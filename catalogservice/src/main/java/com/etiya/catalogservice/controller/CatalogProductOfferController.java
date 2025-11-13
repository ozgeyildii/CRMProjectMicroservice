package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CatalogProductOfferService;
import com.etiya.catalogservice.service.dtos.requests.catalogproductoffer.CreateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.catalogproductoffer.CreatedCatalogProductOfferResponse;
import com.etiya.common.responses.GetCatalogRelByProductOfferId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/catalog-product-offers")
public class CatalogProductOfferController {

    private final CatalogProductOfferService catalogProductOfferService;

    public CatalogProductOfferController(CatalogProductOfferService catalogProductOfferService) {
        this.catalogProductOfferService = catalogProductOfferService;
    }

    @PostMapping
    public CreatedCatalogProductOfferResponse add(@RequestBody CreateCatalogProductOfferRequest request) {
        return catalogProductOfferService.add(request);
    }

    @GetMapping("/{productOfferId}")
    @ResponseStatus(HttpStatus.OK)
    public GetCatalogRelByProductOfferId getByProductOfferId(@PathVariable int productOfferId) {
        return catalogProductOfferService.getByProductOfferId(productOfferId);
    }
}