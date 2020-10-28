package com.epam.registration.controller;

import com.epam.registration.dto.ErrorInfoDto;
import com.epam.registration.exceptions.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResponseEntity<ErrorInfoDto> applicationException(BaseException e) {
        ErrorInfoDto errorInfoDto = new ErrorInfoDto(e.getHttpStatus(), e.getMessage());
        return new ResponseEntity<>(errorInfoDto, errorInfoDto.getHttpStatus());
    }
}