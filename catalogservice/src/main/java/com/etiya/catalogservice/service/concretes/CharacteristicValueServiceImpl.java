package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.CharacteristicValue;
import com.etiya.catalogservice.repository.CharacteristicValueRepository;
import com.etiya.catalogservice.service.abstracts.CharacteristicValueService;
import com.etiya.catalogservice.service.dtos.requests.characteristicvalue.CreateCharacteristicValueRequest;
import com.etiya.catalogservice.service.dtos.responses.characteristicvalue.CreatedCharacteristicValueResponse;
import com.etiya.catalogservice.service.mappings.CharacteristicValueMapper;
import org.springframework.stereotype.Service;

@Service
public class CharacteristicValueServiceImpl implements CharacteristicValueService {

    private final CharacteristicValueRepository characteristicValueRepository;

    public CharacteristicValueServiceImpl(CharacteristicValueRepository characteristicValueRepository) {
        this.characteristicValueRepository = characteristicValueRepository;
    }

    @Override
    public CreatedCharacteristicValueResponse add(CreateCharacteristicValueRequest request) {
        CharacteristicValue characteristicValue = CharacteristicValueMapper.INSTANCE
                .characteristicValueFromCreateCharacteristicValueRequest(request);

        CharacteristicValue created = characteristicValueRepository.save(characteristicValue);

        return CharacteristicValueMapper.INSTANCE
                .createdCharacteristicValueResponseFromCharacteristicValue(created);
    }
}
