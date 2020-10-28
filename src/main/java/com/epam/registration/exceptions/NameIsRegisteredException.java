package com.epam.registration.exceptions;

import org.springframework.http.HttpStatus;

public class NameIsRegisteredException extends BaseException{

    public NameIsRegisteredException(String userName) {
        super(String.format("User with name [%s] is already registered.", userName), HttpStatus.CONFLICT);
    }
}
