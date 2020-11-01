package com.epam.registration.controller;


import com.epam.registration.controller.assembler.IdentificationNumberModelAssembler;
import com.epam.registration.model.IdentificationNumberModel;
import com.epam.registration.dto.IdentificationNumberDto;
import com.epam.registration.service.IdentificationNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = "Identification number management REST API")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@RequestMapping("/api/identification")
@Slf4j
@RestController
@RequiredArgsConstructor
public class IdentificationNumberController {

    private final IdentificationNumberService identificationNumberService;
    private final IdentificationNumberModelAssembler identificationNumberModelAssembler;

    @ApiOperation("Set identification number (access only for ADMIN)")
    @ApiResponse(code = 200, message = "OK", response = IdentificationNumberModel.class)
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public IdentificationNumberModel setIdentificationNumber(@Valid @RequestBody IdentificationNumberDto identificationNumberDto) {
        return identificationNumberModelAssembler.toModel(identificationNumberService.addNewIdentificationNumber(identificationNumberDto));
    }

    @ApiOperation("Get list of all identification numbers")
    @ApiResponse(code = 200, message = "OK", response = CollectionModel.class)
    @GetMapping("/identificationNumber")
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<IdentificationNumberModel> getAllIdentificationNumbers() {
        return identificationNumberModelAssembler.toCollectionModel(identificationNumberService.getAllIdentificationNumbers());
    }
}