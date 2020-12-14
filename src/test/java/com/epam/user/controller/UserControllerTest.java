package com.epam.user.controller;

import com.epam.user.dto.UserDto;
import com.epam.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;


    @InjectMocks
    private UserController userController;

    final Principal principalMock = mock(Principal.class);


    @Test
    void getUserTest() {
        when(principalMock.getName()).thenReturn("username");
        Principal result = userController.getUser(principalMock);
        assertEquals(result, principalMock);
    }

    @Test
    void deleteUserTest() {
        userController.deleteUser(new UserDto("User", "Password0"));
        verify(userService, times(1)).deleteUser(any());
    }

    @Test
    void signOutTest() {
        assertEquals(userController.deleteUser(new UserDto("User", "Password0")), ResponseEntity.noContent().build());
    }

    @Test
    void getAllUsersTest1() {
        userController.getAllUsers();
        verify(userService, times(1)).getAllUsers();
    }

}