package com.epam.user.exceptions;

import org.springframework.http.HttpStatus;

public class AgeException extends BaseException{
    public AgeException() {
        super("User is under the minimum age", HttpStatus.FORBIDDEN);
    }
}
