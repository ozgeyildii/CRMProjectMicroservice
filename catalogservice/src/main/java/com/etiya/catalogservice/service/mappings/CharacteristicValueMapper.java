package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.CharacteristicValue;
import com.etiya.catalogservice.service.dtos.requests.characteristicvalue.CreateCharacteristicValueRequest;
import com.etiya.catalogservice.service.dtos.responses.characteristicvalue.CreatedCharacteristicValueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CharacteristicValueMapper {

    CharacteristicValueMapper INSTANCE = Mappers.getMapper(CharacteristicValueMapper.class);

    @Mapping(target = "characteristic.id", source = "charId")
    CharacteristicValue characteristicValueFromCreateCharacteristicValueRequest(CreateCharacteristicValueRequest request);

    @Mapping(target = "charId", source = "characteristic.id")
    CreatedCharacteristicValueResponse createdCharacteristicValueResponseFromCharacteristicValue(CharacteristicValue characteristicValue);
}
