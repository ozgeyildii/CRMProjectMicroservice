package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.ProdCharValueService;

import com.etiya.catalogservice.service.dtos.requests.productoffercharvalue.CreateProdOfferCharValueRequest;
import com.etiya.catalogservice.service.dtos.responses.productcharvalue.CreatedProdCharValueResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prod-char-values")
public class ProdCharValueController {

    private final ProdCharValueService prodCharValueService;

    public ProdCharValueController(ProdCharValueService prodCharValueService) {
        this.prodCharValueService = prodCharValueService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProdCharValueResponse add(@RequestBody CreateProdOfferCharValueRequest request) {
        return prodCharValueService.add(request);
    }
}
