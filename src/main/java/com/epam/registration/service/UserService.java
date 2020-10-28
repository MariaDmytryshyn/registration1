package com.epam.registration.service;

import com.epam.registration.dto.NewUserDto;
import com.epam.registration.dto.UserDto;
import com.epam.registration.model.User;

import java.util.List;

public interface UserService {
    List<NewUserDto> getAllUsers();
    boolean matchesIIN(User user);
    void deleteUser(UserDto user);
}
