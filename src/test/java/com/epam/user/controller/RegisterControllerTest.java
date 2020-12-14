package com.epam.user.controller;

import com.epam.user.dto.NewUserDto;
import com.epam.user.dto.UserDto;
import com.epam.user.model.enums.Role;
import com.epam.user.service.RegisterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegisterControllerTest {

    @Mock
    private RegisterService registerService;


    @InjectMocks
    private RegisterController registerController;

    @Test
    void signInTest() {
        registerController.signIn(new UserDto("User", "myPassword"));
        verify(registerService, times(1)).signIn(any());
    }

    @Test
    void registerTest() {
        registerController.register(new NewUserDto("User", "myPassword", "myPassword", LocalDate.of(1999, 10, 11), "123456789012345"));
        verify(registerService, times(1)).register(any(), eq(Role.ROLE_USER));
    }

    @Test
    void signOutTest() {
        assertEquals(registerController.signOut(), ResponseEntity.noContent().build());
    }

    @Test
    void singnOutTest2() {
        registerController.signOut();
        verify(registerService, times(1)).signOut();
    }
}