package com.epam.registration.service.impl;

import com.epam.registration.dto.NewUserDto;
import com.epam.registration.dto.UserDto;
import com.epam.registration.exceptions.IdentificationNumberException;
import com.epam.registration.exceptions.NameIsRegisteredException;
import com.epam.registration.model.User;
import com.epam.registration.model.enums.Role;
import com.epam.registration.repository.IdentificationNumberRepository;
import com.epam.registration.repository.UserRepository;
import com.epam.registration.service.RegisterService;
import org.modelmapper.ModelMapper;
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

import static com.epam.registration.service.impl.UserServiceImpl.mapUserToUserDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final IdentificationNumberRepository identificationNumberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDetails loadUserByUsername(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Unable to find user!"));
    }

    @Override
    public NewUserDto signIn(UserDto userDto) {
        UserServiceImpl userService = new UserServiceImpl(userRepository, identificationNumberRepository);
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
        UserServiceImpl userService = new UserServiceImpl(userRepository, identificationNumberRepository);
        User user = modelMapper.map(newUserDto, User.class);
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