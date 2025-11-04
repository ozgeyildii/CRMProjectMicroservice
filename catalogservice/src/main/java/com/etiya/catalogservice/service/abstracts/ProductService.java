package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.responses.CreatedProductResponse;
import com.etiya.common.responses.ProductResponse;

public interface ProductService {

    CreatedProductResponse add(CreateProductRequest request);

    ProductResponse getById(String id);
}