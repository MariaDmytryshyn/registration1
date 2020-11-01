package com.epam.registration.service;

import com.epam.registration.dto.IdentificationNumberDto;

import java.util.List;

public interface IdentificationNumberService {

    IdentificationNumberDto addNewIdentificationNumber(IdentificationNumberDto identificationNumberDto);
    List<IdentificationNumberDto> getAllIdentificationNumbers();
}
