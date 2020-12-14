package com.epam.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationNumberDto {

    @NotBlank
    @Pattern(regexp = "^[0-9]{6}$")
    private String identificationNumber;
}
