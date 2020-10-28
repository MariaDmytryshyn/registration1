package com.epam.registration.controller;

import com.epam.registration.controller.assembler.UserModelAssembler;
import com.epam.registration.dto.UserDto;
import com.epam.registration.model.UserModel;
import com.epam.registration.dto.NewUserDto;
import com.epam.registration.model.enums.Role;
import com.epam.registration.service.RegisterService;
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
@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService authService;
    private final UserModelAssembler modelAssembler;

    @ApiOperation("Sign in user to the system")
    @ApiResponse(code = 200, message = "OK", response = UserModel.class)
    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public UserModel signIn(@Valid @RequestBody UserDto userDto) {
        return modelAssembler.toModel(authService.signIn(userDto));
    }

    @ApiOperation("Register user to the system")
    @ApiResponse(code = 201, message = "Created", response = UserModel.class)
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel register(@Valid @RequestBody NewUserDto newUserDto) {
        return modelAssembler.toModel(authService.register(newUserDto, Role.ROLE_USER));
    }

    @ApiOperation("Sign out current user from the system")
    @ApiResponse(code = 204, message = "No content")
    @GetMapping("/singout")
    public ResponseEntity<Void> signOut() {
        authService.signOut();
        return ResponseEntity.noContent().build();
    }

}