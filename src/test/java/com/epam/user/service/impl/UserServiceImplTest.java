package com.epam.user.service.impl;

import com.epam.user.dto.NewUserDto;
import com.epam.user.dto.UserDto;
import com.epam.user.model.User;
import com.epam.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static com.epam.user.service.impl.UserServiceImpl.mapUserToUserDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    public static final String TEST_PASSWORD = "myPassword0";
    public static final ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

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

//    @Test
//    void matchesIINAdminTest() {
//        User user = createTestUser();
//        user.setRole(Role.ROLE_ADMIN);
//        assertFalse(userService.matchesIIN(user));
//    }
}