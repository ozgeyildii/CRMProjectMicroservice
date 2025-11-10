package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.ProdSpecCharacteristicService;

import com.etiya.catalogservice.service.dtos.requests.productofferspeccharacteristic.CreateProdOfferSpecCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.productspeccharacteristic.CreatedProdSpecCharacteristicResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prod-spec-characteristics")
public class ProdSpecCharacteristicController {

    private final ProdSpecCharacteristicService prodSpecCharacteristicService;

    public ProdSpecCharacteristicController(ProdSpecCharacteristicService prodSpecCharacteristicService) {
        this.prodSpecCharacteristicService = prodSpecCharacteristicService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProdSpecCharacteristicResponse add(@RequestBody CreateProdOfferSpecCharacteristicRequest request) {
        return prodSpecCharacteristicService.add(request);
    }
}
