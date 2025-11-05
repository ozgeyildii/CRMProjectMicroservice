package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.ProdSpecCharacteristic;

import com.etiya.catalogservice.service.dtos.requests.productspeccharacteristic.CreateProdSpecCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.productspeccharacteristic.CreatedProdSpecCharacteristicResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProdSpecCharacteristicMapper {

    ProdSpecCharacteristicMapper INSTANCE = Mappers.getMapper(ProdSpecCharacteristicMapper.class);

    @Mapping(target = "productSpecification.id", source = "specId")
    @Mapping(target = "characteristic.id", source = "characteristicId")
    ProdSpecCharacteristic prodSpecCharacteristicFromCreateProdSpecCharacteristicRequest(CreateProdSpecCharacteristicRequest request);

    @Mapping(target = "specId", source = "productSpecification.id")
    @Mapping(target = "characteristicId", source = "characteristic.id")
    CreatedProdSpecCharacteristicResponse createdProdSpecCharacteristicResponseFromProdSpecCharacteristic(ProdSpecCharacteristic prodSpecCharacteristic);
}
