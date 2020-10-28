package com.epam.registration.controller;

import com.epam.registration.dto.ErrorInfoDto;
import com.epam.registration.exceptions.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResponseEntity<ErrorInfoDto> applicationException(BaseException e) {
        ErrorInfoDto errorInfoDto = new ErrorInfoDto(e.getHttpStatus(), e.getMessage());
        return new ResponseEntity<>(errorInfoDto, errorInfoDto.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorInfoDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        if (ex.getMessage().contains("Checks if the user is not under the minimal age")) {
            ErrorInfoDto errorInfoDto = new ErrorInfoDto(HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
            return new ResponseEntity<>(errorInfoDto, errorInfoDto.getHttpStatus());
        }
        ErrorInfoDto errorInfoDto = new ErrorInfoDto(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorInfoDto, errorInfoDto.getHttpStatus());
    }
}