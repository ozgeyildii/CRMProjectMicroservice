package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.ProdOfferSpecCharacteristic;

import com.etiya.catalogservice.service.dtos.requests.productofferspeccharacteristic.CreateProdOfferSpecCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.productspeccharacteristic.CreatedProdSpecCharacteristicResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProdSpecCharacteristicMapper {

    ProdSpecCharacteristicMapper INSTANCE = Mappers.getMapper(ProdSpecCharacteristicMapper.class);

    @Mapping(target = "productOfferSpecification.id", source = "productOfferSpecId")
    @Mapping(target = "characteristic.id", source = "characteristicId")
    ProdOfferSpecCharacteristic prodSpecCharacteristicFromCreateProdSpecCharacteristicRequest(CreateProdOfferSpecCharacteristicRequest request);

    @Mapping(target = "productOfferSpecId", source = "productOfferSpecification.id")
    @Mapping(target = "characteristicId", source = "characteristic.id")
    CreatedProdSpecCharacteristicResponse createdProdSpecCharacteristicResponseFromProdSpecCharacteristic(ProdOfferSpecCharacteristic prodOfferSpecCharacteristic);
}
