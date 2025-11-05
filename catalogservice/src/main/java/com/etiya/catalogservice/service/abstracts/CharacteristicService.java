package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.characteristic.CreateCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.characteristic.CreatedCharacteristicResponse;

public interface CharacteristicService {

    CreatedCharacteristicResponse add(CreateCharacteristicRequest request);
}
