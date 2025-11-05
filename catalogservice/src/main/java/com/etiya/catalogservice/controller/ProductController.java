package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.dtos.requests.product.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.responses.product.CreatedProductResponse;
import com.etiya.common.responses.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductResponse add(@RequestBody CreateProductRequest request){
        return productService.add(request);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getById(@PathVariable int id){
        return productService.getById(id);
    }
}