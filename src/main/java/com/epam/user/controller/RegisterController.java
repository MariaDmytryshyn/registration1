package com.epam.user.controller;


import com.epam.user.dto.NewUserDto;
import com.epam.user.dto.UserDto;
import com.epam.user.model.enums.Role;
import com.epam.user.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = "Authentication management REST API")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@RequestMapping("/user/auth")
@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService authService;

    @ApiOperation("Sign in user to the system")
    @ApiResponse(code = 200, message = "OK", response = NewUserDto.class)
    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public NewUserDto signIn(@Valid @RequestBody UserDto userDto) {
        return authService.signIn(userDto);
    }

    @ApiOperation("Register user to the system")
    @ApiResponse(code = 201, message = "Created", response = NewUserDto.class)
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserDto register(@Valid @RequestBody NewUserDto newUserDto) {
        return authService.register(newUserDto, Role.ROLE_USER);
    }

    @ApiOperation("Sign out current user from the system")
    @ApiResponse(code = 204, message = "No content")
    @GetMapping("/singout")
    public ResponseEntity<Void> signOut() {
        authService.signOut();
        return ResponseEntity.noContent().build();
    }

}