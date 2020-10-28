package com.epam.registration.controller;

import com.epam.registration.controller.assembler.UserModelAssembler;
import com.epam.registration.dto.UserDto;
import com.epam.registration.model.UserModel;
import com.epam.registration.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Api(tags = "User management REST API")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@RequestMapping("/api/user")
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserModelAssembler modelAssembler;

    @ApiOperation("Get user")
    @ApiResponse(code = 200, message = "OK", response = UserModel.class)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Principal getUser(Principal principal) {
        log.info("GetUser with name {}", principal.getName());
        return principal;
    }

    @ApiOperation("Delete user")
    @ApiResponse(code = 204, message = "No content")
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(UserDto user) {
        log.info("deleteUser: with username {}", user.getUserName());
        userService.deleteUser(user);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Get list of all users")
    @ApiResponse(code = 200, message = "OK", response = CollectionModel.class)
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<UserModel> getAllUsers() {
        return modelAssembler.toCollectionModel(userService.getAllUsers());
    }

}
