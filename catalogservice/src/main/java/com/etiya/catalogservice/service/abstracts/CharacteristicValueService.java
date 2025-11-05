package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.characteristicvalue.CreateCharacteristicValueRequest;
import com.etiya.catalogservice.service.dtos.responses.characteristicvalue.CreatedCharacteristicValueResponse;

public interface CharacteristicValueService {

    CreatedCharacteristicValueResponse add(CreateCharacteristicValueRequest request);
}
