package com.epam.registration.controller;

import com.epam.registration.dto.ErrorInfoDto;
import com.epam.registration.exceptions.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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
        if (ex.getBindingResult().hasFieldErrors()){
        if (Objects.equals(ex.getBindingResult().getFieldError().getCode(), "Past")) {
            ErrorInfoDto errorInfoDto = new ErrorInfoDto(HttpStatus.BAD_REQUEST, ex.getBindingResult().getFieldError().getDefaultMessage());
            return new ResponseEntity<>(errorInfoDto, errorInfoDto.getHttpStatus());
        }
        if (Objects.equals(ex.getBindingResult().getFieldError().getCode(), "AgeValidation")) {
            ErrorInfoDto errorInfoDto = new ErrorInfoDto(HttpStatus.FORBIDDEN, ex.getBindingResult().getFieldError().getDefaultMessage());
            return new ResponseEntity<>(errorInfoDto, errorInfoDto.getHttpStatus());
        }}
        if (ex.getBindingResult().hasGlobalErrors()) {
            ErrorInfoDto errorInfoDto = new ErrorInfoDto(HttpStatus.BAD_REQUEST, ex.getBindingResult().getGlobalError().getDefaultMessage());
            return new ResponseEntity<>(errorInfoDto, errorInfoDto.getHttpStatus());
        }
        ErrorInfoDto errorInfoDto = new ErrorInfoDto(HttpStatus.BAD_REQUEST, ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(errorInfoDto, errorInfoDto.getHttpStatus());
    }
}