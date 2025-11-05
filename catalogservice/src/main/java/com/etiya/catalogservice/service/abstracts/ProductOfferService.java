package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.ProductOffer;
import com.etiya.catalogservice.service.dtos.requests.productoffer.CreateProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.productoffer.CreatedProductOfferResponse;

public interface ProductOfferService {
    CreatedProductOfferResponse add(CreateProductOfferRequest request);
    ProductOffer getById(int id);
}