package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.Characteristic;
import com.etiya.catalogservice.repository.CharacteristicRepository;
import com.etiya.catalogservice.service.abstracts.CharacteristicService;
import com.etiya.catalogservice.service.dtos.requests.characteristic.CreateCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.characteristic.CreatedCharacteristicResponse;
import com.etiya.catalogservice.service.mappings.CharacteristicMapper;
import org.springframework.stereotype.Service;

@Service
public class CharacteristicServiceImpl implements CharacteristicService {

    private final CharacteristicRepository characteristicRepository;

    public CharacteristicServiceImpl(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    @Override
    public CreatedCharacteristicResponse add(CreateCharacteristicRequest request) {
        Characteristic characteristic = CharacteristicMapper.INSTANCE.characteristicFromCreateCharacteristicRequest(request);
        Characteristic createdCharacteristic = characteristicRepository.save(characteristic);
        return CharacteristicMapper.INSTANCE.createdCharacteristicResponseFromCharacteristic(createdCharacteristic);
    }
}
