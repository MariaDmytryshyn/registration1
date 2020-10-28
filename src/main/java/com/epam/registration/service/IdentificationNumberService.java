package com.epam.registration.service;

import com.epam.registration.dto.IdentificationNumberDto;

import java.util.List;

public interface IdentificationNumberService {

    IdentificationNumberDto addNewCode(IdentificationNumberDto identificationNumberDto);
    List<IdentificationNumberDto> getAllCodes();
}
