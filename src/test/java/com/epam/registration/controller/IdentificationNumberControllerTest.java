package com.epam.registration.controller;


import com.epam.registration.controller.assembler.IdentificationNumberModelAssembler;
import com.epam.registration.dto.IdentificationNumberDto;
import com.epam.registration.service.IdentificationNumberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IdentificationNumberControllerTest {

    @Mock
    private IdentificationNumberService identificationNumberService;

    @Mock
    private IdentificationNumberModelAssembler identificationNumberModelAssembler;

    @InjectMocks
    private IdentificationNumberController identificationNumberController;

    @Test
    void setIdentificationNumberTest() {
        identificationNumberController.setIdentificationNumber(new IdentificationNumberDto("123454"));
        verify(identificationNumberService, times(1)).addNewIdentificationNumber(any());
    }

    @Test
    void getAllIdentificationNumbersTest() {
        identificationNumberController.getAllIdentificationNumbers();
        verify(identificationNumberService, times(1)).getAllIdentificationNumbers();
    }
}