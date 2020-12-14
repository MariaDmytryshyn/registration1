package com.epam.user.service.impl;

import com.epam.user.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterServiceImplTest {

    @InjectMocks
    private RegisterServiceImpl registerService;

    @Mock
    private UserRepository userRepository;

    final UserDetails userDetails = mock(UserDetails.class);

    @Test
    void loadUserByUsernameTest() {
        assertThrows(UsernameNotFoundException.class, () -> registerService.loadUserByUsername("userName"));
    }

//    @Test
//    void signInTest() {
//
//    }
}