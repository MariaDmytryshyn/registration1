package com.epam.registration.controller.assembler;

import com.epam.registration.controller.IdentificationNumberController;
import com.epam.registration.model.IdentificationNumberModel;
import com.epam.registration.dto.IdentificationNumberDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IdentificationNumberModelAssembler extends RepresentationModelAssemblerSupport<IdentificationNumberDto, IdentificationNumberModel> {

    public IdentificationNumberModelAssembler() {
        super(IdentificationNumberController.class, IdentificationNumberModel.class);
    }

    @Override
    public IdentificationNumberModel toModel(IdentificationNumberDto entity) {
        return new IdentificationNumberModel(entity);
    }

    public CollectionModel<IdentificationNumberModel> toCollectionModel(List<IdentificationNumberDto> identificationNumberDtos) {
        List<IdentificationNumberModel> identificationNumberModels = identificationNumberDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(identificationNumberModels);
    }

}
