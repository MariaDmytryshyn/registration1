package com.epam.registration.model;

import com.epam.registration.dto.IdentificationNumberDto;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.Valid;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationNumberModel extends RepresentationModel<UserModel> {

    @JsonUnwrapped
    @Valid
    private IdentificationNumberDto identificationNumberDto;

}