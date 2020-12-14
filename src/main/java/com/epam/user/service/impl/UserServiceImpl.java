package com.epam.user.service.impl;

import com.epam.user.client.IdentificationNumberClient;
import com.epam.user.dto.IdentificationNumberDto;
import com.epam.user.dto.NewUserDto;
import com.epam.user.dto.UserDto;
import com.epam.user.model.User;
import com.epam.user.model.enums.Role;
import com.epam.user.repository.UserRepository;
import com.epam.user.service.UserService;
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
    private final IdentificationNumberClient identificationNumberClient;

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
        List<IdentificationNumberDto> identificationNumber = identificationNumberClient.getAllIdentificationNumbers();
        if (!identificationNumber.isEmpty()) {
            for (IdentificationNumberDto i : identificationNumber) {
                if (iin.equals(i.getIdentificationNumber())) {
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
