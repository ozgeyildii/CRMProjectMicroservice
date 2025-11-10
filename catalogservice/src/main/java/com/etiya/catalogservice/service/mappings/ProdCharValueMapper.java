package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.ProdOfferCharValue;

import com.etiya.catalogservice.service.dtos.requests.productoffercharvalue.CreateProdOfferCharValueRequest;
import com.etiya.catalogservice.service.dtos.responses.productcharvalue.CreatedProdCharValueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProdCharValueMapper {

    ProdCharValueMapper INSTANCE = Mappers.getMapper(ProdCharValueMapper.class);

    @Mapping(target = "characteristicValue.id", source = "charValueId")
    @Mapping(target = "productOffer.id", source = "productOfferId")
    ProdOfferCharValue prodCharValueFromCreateProdCharValueRequest(CreateProdOfferCharValueRequest request);

    @Mapping(target = "charValueId", source = "characteristicValue.id")
    @Mapping(target = "productOfferId", source = "productOffer.id")
    CreatedProdCharValueResponse createdProdCharValueResponseFromProdCharValue(ProdOfferCharValue prodOfferCharValue);
}
