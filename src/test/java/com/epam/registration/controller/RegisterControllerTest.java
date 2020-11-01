package com.epam.registration.controller;

import com.epam.registration.controller.assembler.IdentificationNumberModelAssembler;
import com.epam.registration.controller.assembler.UserModelAssembler;
import com.epam.registration.dto.IdentificationNumberDto;
import com.epam.registration.dto.NewUserDto;
import com.epam.registration.dto.UserDto;
import com.epam.registration.model.enums.Role;
import com.epam.registration.service.IdentificationNumberService;
import com.epam.registration.service.RegisterService;
import com.epam.registration.service.UserService;
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

    @Mock
    private UserModelAssembler userModelAssembler;

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