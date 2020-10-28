package com.epam.registration.service.impl;

import com.epam.registration.dto.NewUserDto;
import com.epam.registration.dto.UserDto;
import com.epam.registration.model.IdentificationNumber;
import com.epam.registration.model.User;
import com.epam.registration.model.enums.Role;
import com.epam.registration.repository.IdentificationNumberRepository;
import com.epam.registration.repository.UserRepository;
import com.epam.registration.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final ModelMapper modelMapper = new ModelMapper();
    private final UserRepository userRepository;
    private final IdentificationNumberRepository identificationNumberRepository;

    @Override
    public void deleteUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        log.info("Delete user with UserName {}", user.getUsername());
        userRepository.delete(user);
    }

    public static NewUserDto mapUserToUserDto(User user) {
        NewUserDto newUserDto = modelMapper.map(user, NewUserDto.class);
        newUserDto.setPassword(null);
        log.debug("Map from User to UserDto: {}", user);
        return newUserDto;
    }


    @Override
    public boolean matchesIIN(User user) {
        if (user.getRole() != null && user.getRole().equals(Role.ROLE_ADMIN)) {
            return false;

        }
        String iin = user.getCardNumber().substring(0, 6);
        List<IdentificationNumber> identificationNumber = identificationNumberRepository.findAll();
        if (!identificationNumber.isEmpty()) {
            for (IdentificationNumber i : identificationNumber) {
                if (iin.equals(i.getCode())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<NewUserDto> getAllUsers() {
        List<NewUserDto> allUsers = userRepository.findAll().stream()
                .map(UserServiceImpl::mapUserToUserDto)
                .collect(Collectors.toList());
        log.info("Users are found {}", allUsers.size());
        return allUsers;
    }

}
