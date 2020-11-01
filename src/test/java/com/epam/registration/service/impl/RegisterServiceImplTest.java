package com.epam.registration.service.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.registration.dto.NewUserDto;
import com.epam.registration.dto.UserDto;
import com.epam.registration.model.IdentificationNumber;
import com.epam.registration.model.User;
import com.epam.registration.model.enums.Role;
import com.epam.registration.repository.IdentificationNumberRepository;
import com.epam.registration.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static com.epam.registration.service.impl.UserServiceImpl.mapUserToUserDto;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RegisterServiceImplTest {

    @InjectMocks
    private RegisterServiceImpl registerService;

    @Mock
    private UserRepository userRepository;

    final UserDetails userDetails = mock(UserDetails.class);

//    @Test
//    void loadUserByUsernameTest() {
//        when(registerService.loadUserByUsername("userName")).t
//        verify(userRepository, times(1)).findByUserName(anyString());
//    }



}