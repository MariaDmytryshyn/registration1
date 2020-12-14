package com.epam.user.service;

import com.epam.user.dto.NewUserDto;
import com.epam.user.dto.UserDto;
import com.epam.user.model.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public interface RegisterService extends UserDetailsService {
    NewUserDto register(@Valid NewUserDto newUserDto, Role role);

    NewUserDto signIn(UserDto userDto);

    void signOut();
}
