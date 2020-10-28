package com.epam.registration.service;

import com.epam.registration.dto.NewUserDto;
import com.epam.registration.dto.UserDto;
import com.epam.registration.model.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.validation.Valid;

public interface RegisterService extends UserDetailsService {
    NewUserDto register(@Valid NewUserDto NewUserDto, Role role);
    NewUserDto signIn(UserDto userDto);
    void signOut();
}
