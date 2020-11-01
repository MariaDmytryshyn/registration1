package com.epam.registration.service.impl;

import com.epam.registration.dto.IdentificationNumberDto;
import com.epam.registration.model.IdentificationNumber;
import com.epam.registration.repository.IdentificationNumberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.epam.registration.service.impl.IdentificationNumberServiceImpl.mapIdentificationNumberDtoToIdentificationNumber;
import static com.epam.registration.service.impl.IdentificationNumberServiceImpl.mapIdentificationNumberToIdentificationNumberDto;


@ExtendWith(MockitoExtension.class)
class IdentificationNumberServiceImplTest {

    @InjectMocks
    private IdentificationNumberServiceImpl identificationNumberService;

    @Mock
    private IdentificationNumberRepository identificationNumberRepository;

    @Test
    void addNewIdentificationNumberTest() {
        identificationNumberService.addNewIdentificationNumber(new IdentificationNumberDto("123456"));
        verify(identificationNumberRepository, times(1)).save(any());
    }

    @Test
    void getAllIdentificationNumbersTest() {
        identificationNumberService.getAllIdentificationNumbers();
        verify(identificationNumberRepository, times(1)).findAll();
    }

    @Test
    void mapIdentificationNumberDtoToIdentificationNumberTest() {
        IdentificationNumberDto identificationNumberDto = new IdentificationNumberDto("123456");
        assertEquals(identificationNumberDto.getIdentificationNumber(), mapIdentificationNumberDtoToIdentificationNumber(identificationNumberDto).getIdentificationNumber());
    }

    @Test
    void mapIdentificationNumberToIdentificationNumberDtoTest() {
        IdentificationNumber identificationNumberDto = new IdentificationNumber(1L, "123456");
        assertEquals(identificationNumberDto.getIdentificationNumber(), mapIdentificationNumberToIdentificationNumberDto(identificationNumberDto).getIdentificationNumber());
    }
}