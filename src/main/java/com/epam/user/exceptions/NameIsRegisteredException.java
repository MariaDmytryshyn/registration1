package com.epam.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NameIsRegisteredException extends BaseException {

    public NameIsRegisteredException(String userName) {
        super(String.format("User with name [%s] is already registered.", userName), HttpStatus.CONFLICT);
    }
}
