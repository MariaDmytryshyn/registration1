package com.epam.registration.exceptions;

import com.epam.registration.model.User;
import org.springframework.http.HttpStatus;

public class AgeException extends BaseException{

    public AgeException(User user) {
        super(String.format("The user [%s] is under the minimal age", user.toString()), HttpStatus.FORBIDDEN);
    }
}
