package com.epam.registration.dto;

import com.epam.registration.dto.validation.AgeValidation;
import com.epam.registration.dto.validation.FieldMatch;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@ApiModel(description = "User data")
@NoArgsConstructor
@FieldMatch(first = "password", second = "repeatPassword")
public class NewUserDto {

    @NotBlank
    @Pattern(regexp="[a-zA-Z0-9_]{4,}", message = "username is invalid")
    private String userName;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Past
    @AgeValidation
    private LocalDate dateOfBirth;

    @NotBlank
    @Pattern(regexp="[0-9]{15,19}", message = "card number is invalid")
    private String cardNumber;

    @NotBlank
    @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,})", message = "password is invalid")
    private String password;
    private String repeatPassword;

}