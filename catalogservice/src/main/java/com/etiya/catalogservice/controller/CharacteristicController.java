package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CharacteristicService;
import com.etiya.catalogservice.service.dtos.requests.characteristic.CreateCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.characteristic.CreatedCharacteristicResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/characteristics")
public class CharacteristicController {

    private final CharacteristicService characteristicService;

    public CharacteristicController(CharacteristicService characteristicService) {
        this.characteristicService = characteristicService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCharacteristicResponse add(@RequestBody CreateCharacteristicRequest request) {
        return characteristicService.add(request);
    }
}
