package com.epam.registration.exceptions;

import com.epam.registration.model.User;
import org.springframework.http.HttpStatus;

public class IdentificationNumberException extends BaseException{

    public IdentificationNumberException(User user) {
        super(String.format("The user's [%s] identification number is blocked", user.toString()), HttpStatus.NOT_ACCEPTABLE);
    }
}
