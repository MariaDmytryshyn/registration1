package com.epam.registration.controller.assembler;

import com.epam.registration.controller.UserController;
import com.epam.registration.model.UserModel;
import com.epam.registration.dto.NewUserDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<NewUserDto, UserModel> {

    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(NewUserDto entity) {
        return new UserModel(entity);
    }

    public CollectionModel<UserModel> toCollectionModel(List<NewUserDto> newUserDtos) {
        List<UserModel> userModels = newUserDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());

        Link self = linkTo((methodOn(UserController.class).getAllUsers())).withSelfRel();
        return CollectionModel.of(userModels).add(self);
    }

}