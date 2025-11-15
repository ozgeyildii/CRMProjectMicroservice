package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.Product;
import com.etiya.catalogservice.domain.ProductOfferSpecification;
import com.etiya.catalogservice.service.dtos.requests.product.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.responses.product.CreatedProductResponse;
import com.etiya.common.responses.ProductOfferResponse;

public interface ProductService {

    CreatedProductResponse add(CreateProductRequest request);
    void addProductByEntity(Product product);

//    ProductOfferResponse getById(int id);
//
//    Product getProductById(int id);
}