package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.ProductSpecificationService;
import com.etiya.catalogservice.service.dtos.requests.productspec.CreateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.responses.productspec.CreatedProductSpecificationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-specification/")
public class ProductSpecificationController {
    private final ProductSpecificationService productSpecificationService;

    public ProductSpecificationController(ProductSpecificationService productSpecificationService) {
        this.productSpecificationService = productSpecificationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductSpecificationResponse add(@RequestBody CreateProductSpecificationRequest request){
        return productSpecificationService.add(request);
    }

}
