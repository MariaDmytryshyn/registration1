package com.epam.user.client;


import com.epam.user.dto.IdentificationNumberDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "identification-service", url = "http://identification-service:8080/api/identification")
public interface IdentificationNumberClient {

    @GetMapping( value = "/identificationNumber")
    List<IdentificationNumberDto> getAllIdentificationNumbers();
}
