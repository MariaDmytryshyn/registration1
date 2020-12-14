package com.epam.user.dto;


import com.epam.user.dto.validation.FieldMatch;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldMatch(first = "password", second = "repeatPassword")
public class NewUserDto {

    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9_]{4,}", message = "Username is invalid")
    private String userName;

    @NotBlank
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,})", message = "Password is invalid")
    private String password;
    private String repeatPassword;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past(message = "The date of birth should be in past")
    private LocalDate dateOfBirth;

    @NotBlank
    @Pattern(regexp = "[0-9]{15,19}", message = "Card number is invalid")
    private String cardNumber;

}