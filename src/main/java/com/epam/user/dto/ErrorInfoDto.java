package com.epam.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
public class ErrorInfoDto {

    private HttpStatus httpStatus;
    private String message;
}
