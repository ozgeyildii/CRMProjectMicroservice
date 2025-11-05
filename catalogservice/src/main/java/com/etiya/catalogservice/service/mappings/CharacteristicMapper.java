package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.Characteristic;
import com.etiya.catalogservice.service.dtos.requests.characteristic.CreateCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.characteristic.CreatedCharacteristicResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CharacteristicMapper {

    CharacteristicMapper INSTANCE = Mappers.getMapper(CharacteristicMapper.class);

    Characteristic characteristicFromCreateCharacteristicRequest(CreateCharacteristicRequest request);

    CreatedCharacteristicResponse createdCharacteristicResponseFromCharacteristic(Characteristic characteristic);
}
