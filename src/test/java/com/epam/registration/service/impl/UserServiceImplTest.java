package com.epam.registration.service.impl;

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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static com.epam.registration.service.impl.UserServiceImpl.mapUserToUserDto;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    public static final String TEST_PASSWORD = "myPassword0";
    public static final ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private IdentificationNumberRepository identificationNumberRepository;


    @Test
    void deleteUserTest() {
        User testUser = createTestUser();
        UserDto userDto = modelMapper.map(testUser, UserDto.class);
        userService.deleteUser(userDto);
        verify(userRepository, times(1)).delete(testUser);
    }

    @Test
    void deleteUserWithExceptionTest() {
        doThrow(RuntimeException.class).when(userRepository).delete(any());
        assertThrows(RuntimeException.class,
                () -> userService.deleteUser(new UserDto()));
    }

    @Test
    void getAllUsersTest() {
        userService.getAllUsers();
        verify(userRepository, times(1)).findAll();
    }

    private User createTestUser() {
        User user = new User();
        user.setUserName("TestName");
        user.setPassword(TEST_PASSWORD);
        return user;
    }

    @Test
    void mapUserToUserDtoTest() {
        User user = createTestUser();
        NewUserDto userDto = mapUserToUserDto(user);
        assertEquals(user.getUsername(), userDto.getUserName());

    }

    @Test
    void matchesIINAdminTest() {
        User user = createTestUser();
        user.setRole(Role.ROLE_ADMIN);
        assertFalse(userService.matchesIIN(user));
    }

    @Test
    void matchesIINFalse() {
        User user = createTestUser();
        user.setCardNumber("123456789012345");
        identificationNumberRepository.save(new IdentificationNumber(1L, "123458"));
        assertFalse(userService.matchesIIN(user));
    }

}