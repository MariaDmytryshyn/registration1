package com.epam.registration.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ApiModel(description = "Code data")
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationNumberDto {

    @NotBlank
    @Pattern(regexp = "^[0-9]{6}$")
    private String identificationNumber;
}
