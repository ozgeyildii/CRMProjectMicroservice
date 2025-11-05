package com.etiya.catalogservice.api.controllers;

import com.etiya.catalogservice.service.abstracts.CharacteristicValueService;
import com.etiya.catalogservice.service.dtos.requests.characteristicvalue.CreateCharacteristicValueRequest;
import com.etiya.catalogservice.service.dtos.responses.characteristicvalue.CreatedCharacteristicValueResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/characteristic-values")
public class CharacteristicValueController {

    private final CharacteristicValueService characteristicValueService;

    public CharacteristicValueController(CharacteristicValueService characteristicValueService) {
        this.characteristicValueService = characteristicValueService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCharacteristicValueResponse add(@RequestBody CreateCharacteristicValueRequest request) {
        return characteristicValueService.add(request);
    }
}
