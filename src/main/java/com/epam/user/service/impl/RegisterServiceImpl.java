package com.epam.user.service.impl;

import com.epam.user.client.IdentificationNumberClient;
import com.epam.user.dto.NewUserDto;
import com.epam.user.dto.UserDto;
import com.epam.user.dto.validation.AgeIsValid;
import com.epam.user.exceptions.AgeException;
import com.epam.user.exceptions.IdentificationNumberException;
import com.epam.user.exceptions.NameIsRegisteredException;
import com.epam.user.model.User;
import com.epam.user.model.enums.Role;
import com.epam.user.repository.UserRepository;
import com.epam.user.service.RegisterService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.epam.user.service.impl.UserServiceImpl.mapUserToUserDto;


@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;

    private final IdentificationNumberClient identificationNumberClient;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Boolean isPresent = userRepository.findByUserName(username).isPresent();
//        if (!isPresent) {
//            throw new UsernameNotFoundException("Username: " + username + " not found");
//        }
//        User user = userRepository.findByUserName(username).get();
//        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//                .commaSeparatedStringToAuthorityList("ROLE_" + user.getRole());
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Unable to find username!"));
    }




    @Override
    public NewUserDto signIn(UserDto userDto) {
        UserServiceImpl userService = new UserServiceImpl(userRepository, identificationNumberClient);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getUserName(),
                        userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        if (userService.matchesIIN(user)) {
            userService.deleteUser(userDto);
            throw new IdentificationNumberException(user);
        }
        return mapUserToUserDto(user);
    }

    @Override
    public NewUserDto register(NewUserDto newUserDto, Role role) {
        UserServiceImpl userService = new UserServiceImpl(userRepository, identificationNumberClient);
        User user = modelMapper.map(newUserDto, User.class);
        if (!(new AgeIsValid().valid(user))) {
            throw new AgeException();
        }
        if (userRepository.findByUserName(user.getUsername()).isPresent()) {
            throw new NameIsRegisteredException(user.getUsername());
        }
        if (!role.equals(Role.ROLE_ADMIN) && userService.matchesIIN(user)) {
            throw new IdentificationNumberException(user);
        }
        log.info("Register a new user with username {}", user.getUsername());
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        log.info("User with id {} successfully registered", user.getId());
        return signIn(new UserDto(newUserDto.getUserName(), newUserDto.getPassword()));
    }

    @Override
    public void signOut() {
        SecurityContextHolder.clearContext();
    }
}