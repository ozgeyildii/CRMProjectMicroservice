package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.Product;
import com.etiya.catalogservice.service.dtos.requests.product.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.responses.product.CreatedProductResponse;
import com.etiya.catalogservice.service.dtos.responses.product.GetProductDetailResponse;

import java.util.List;

public interface ProductService {

    CreatedProductResponse add(CreateProductRequest request);
    Product addProductByEntity(Product product);
   // List<GetProductDetailResponse> getProducts(int billingAccountId);
//    ProductOfferResponse getById(int id);
//
//    Product getProductById(int id);
}