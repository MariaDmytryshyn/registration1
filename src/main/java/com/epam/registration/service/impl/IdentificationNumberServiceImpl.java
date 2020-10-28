package com.epam.registration.service.impl;

import com.epam.registration.dto.IdentificationNumberDto;
import com.epam.registration.model.IdentificationNumber;
import com.epam.registration.repository.IdentificationNumberRepository;
import com.epam.registration.service.IdentificationNumberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class IdentificationNumberServiceImpl implements IdentificationNumberService {

    private final IdentificationNumberRepository identificationNumberRepository;

    @Override
    public IdentificationNumberDto addNewCode(IdentificationNumberDto identificationNumberDto) {
        IdentificationNumber identificationNumber = mapCodeDtoToUser(identificationNumberDto);
        identificationNumber = identificationNumberRepository.save(identificationNumber);
        log.info("Codes with id {} successfully registered", identificationNumber.getId());
        return identificationNumberDto;
    }

    @Override
    public List<IdentificationNumberDto> getAllCodes() {
        List<IdentificationNumberDto> allCodes = identificationNumberRepository.findAll().stream()
                .map(IdentificationNumberServiceImpl::mapCodesToCodeDto)
                .collect(Collectors.toList());
        log.info("Codes are found {}", allCodes.size());
        return allCodes;

    }

    static IdentificationNumber mapCodeDtoToUser(IdentificationNumberDto identificationNumberDto) {
        ModelMapper modelMapper = new ModelMapper();
        IdentificationNumber identificationNumber = modelMapper.map(identificationNumberDto, IdentificationNumber.class);
        log.debug("Map to Codes from CodeDto: {}", identificationNumberDto);
        return identificationNumber;
    }

    public static IdentificationNumberDto mapCodesToCodeDto(IdentificationNumber identificationNumber) {
        ModelMapper modelMapper = new ModelMapper();
        IdentificationNumberDto identificationNumberDto = modelMapper.map(identificationNumber, IdentificationNumberDto.class);
        log.debug("Map from Codes to CodeDto: {}", identificationNumber);
        return identificationNumberDto;
    }
}
