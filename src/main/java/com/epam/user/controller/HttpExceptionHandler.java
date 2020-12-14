package com.epam.user.controller;

import com.epam.user.dto.ErrorInfoDto;
import com.epam.user.exceptions.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

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
        log.error(ex.getBindingResult().getAllErrors().toString());
        List<ObjectError> errs = ex.getBindingResult().getAllErrors();
        List<String> messages = new ArrayList<>();
        for (ObjectError error : errs) {
            messages.add(error.getDefaultMessage());
        }
        String message = String.join(" ", messages);
        ErrorInfoDto errorInfoDto = new ErrorInfoDto(HttpStatus.BAD_REQUEST, message);
        return new ResponseEntity<>(errorInfoDto, errorInfoDto.getHttpStatus());
    }
}