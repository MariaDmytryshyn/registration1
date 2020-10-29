package com.epam.registration.service.impl;

import com.epam.registration.dto.UserDto;
import com.epam.registration.model.User;
import com.epam.registration.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


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

    private User createTestUser() {
        User user = new User();
        user.setUserName("TestName");
        user.setPassword(TEST_PASSWORD);
        return user;
    }
}