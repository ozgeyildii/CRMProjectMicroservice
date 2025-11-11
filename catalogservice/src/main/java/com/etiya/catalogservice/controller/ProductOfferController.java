package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.domain.ProductOffer;
import com.etiya.catalogservice.service.abstracts.ProductOfferService;
import com.etiya.catalogservice.service.dtos.requests.productoffer.CreateProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.productoffer.CreatedProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.productoffer.GetListProductOfferResponse;
import com.etiya.common.responses.ProductOfferResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-offers")
public class ProductOfferController {

    private final ProductOfferService productOfferService;

    public ProductOfferController(ProductOfferService productOfferService) {
        this.productOfferService = productOfferService;
    }

    @PostMapping
    public CreatedProductOfferResponse add(@RequestBody CreateProductOfferRequest request) {
        return productOfferService.add(request);
    }

    @GetMapping("/{id}")
    public ProductOfferResponse getById(@PathVariable int id) {
        return productOfferService.getById(id);
    }

    @GetMapping("/get-by-catalog/{catalogId}")
    public List<GetListProductOfferResponse> getAllByCatalogId(@PathVariable int catalogId) {
        return productOfferService.getAllByCatalogId(catalogId);
    }
}