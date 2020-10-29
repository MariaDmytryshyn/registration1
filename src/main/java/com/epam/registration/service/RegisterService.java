package com.epam.registration.service;

import com.epam.registration.dto.NewUserDto;
import com.epam.registration.dto.UserDto;
import com.epam.registration.model.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public interface RegisterService extends UserDetailsService {
    NewUserDto register(@Valid NewUserDto newUserDto, Role role);
    NewUserDto signIn(UserDto userDto);
    void signOut();
}
