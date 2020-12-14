package com.epam.user.service;



import com.epam.user.dto.NewUserDto;
import com.epam.user.dto.UserDto;
import com.epam.user.model.User;

import java.util.List;

public interface UserService {
    List<NewUserDto> getAllUsers();

    boolean matchesIIN(User user);
    void deleteUser(UserDto user);
}
