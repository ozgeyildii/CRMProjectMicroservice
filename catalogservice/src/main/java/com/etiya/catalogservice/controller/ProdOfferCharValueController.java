package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.ProdOfferCharValueService;

import com.etiya.catalogservice.service.dtos.requests.productoffercharvalue.CreateProdOfferCharValueRequest;
import com.etiya.catalogservice.service.dtos.requests.productoffercharvalue.GetCharacteristicsByProductOffersRequest;
import com.etiya.catalogservice.service.dtos.responses.productcharvalue.CreatedProdCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.productcharvalue.GetCharacteristicsByProductOffersResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prod-char-values")
public class ProdOfferCharValueController {

    private final ProdOfferCharValueService prodOfferCharValueService;

    public ProdOfferCharValueController(ProdOfferCharValueService prodOfferCharValueService) {
        this.prodOfferCharValueService = prodOfferCharValueService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProdCharValueResponse add(@RequestBody CreateProdOfferCharValueRequest request) {
        return prodOfferCharValueService.add(request);
    }

    @PostMapping("/detail")
    public ResponseEntity<List<GetCharacteristicsByProductOffersResponse>> getCharacteristicsByProductOffers(
            @RequestBody GetCharacteristicsByProductOffersRequest request
    ) {
        List<GetCharacteristicsByProductOffersResponse> responses = prodOfferCharValueService.getCharacteristicsByProductOffers(request);
        return ResponseEntity.ok(responses);
    }
}
