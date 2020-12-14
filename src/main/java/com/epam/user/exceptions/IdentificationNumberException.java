package com.epam.user.exceptions;


import com.epam.user.model.User;
import org.springframework.http.HttpStatus;

public class IdentificationNumberException extends BaseException{

    public IdentificationNumberException(User user) {
        super(String.format("The user's [%s] identification number is blocked", user.toString()), HttpStatus.NOT_ACCEPTABLE);
    }
}
