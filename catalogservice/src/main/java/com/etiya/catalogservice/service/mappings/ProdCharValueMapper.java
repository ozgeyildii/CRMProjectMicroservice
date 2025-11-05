package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.ProdCharValue;

import com.etiya.catalogservice.service.dtos.requests.productcharvalue.CreateProdCharValueRequest;
import com.etiya.catalogservice.service.dtos.responses.productcharvalue.CreatedProdCharValueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProdCharValueMapper {

    ProdCharValueMapper INSTANCE = Mappers.getMapper(ProdCharValueMapper.class);

    @Mapping(target = "characteristicValue.id", source = "charValueId")
    @Mapping(target = "product.id", source = "productId")
    ProdCharValue prodCharValueFromCreateProdCharValueRequest(CreateProdCharValueRequest request);

    @Mapping(target = "charValueId", source = "characteristicValue.id")
    @Mapping(target = "productId", source = "product.id")
    CreatedProdCharValueResponse createdProdCharValueResponseFromProdCharValue(ProdCharValue prodCharValue);
}
