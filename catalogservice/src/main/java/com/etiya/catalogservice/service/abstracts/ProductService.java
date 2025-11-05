package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.Product;
import com.etiya.catalogservice.service.dtos.requests.product.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.responses.product.CreatedProductResponse;
import com.etiya.common.responses.ProductResponse;

public interface ProductService {

    CreatedProductResponse add(CreateProductRequest request);

    ProductResponse getById(int id);

    Product getProductById(int id);
}