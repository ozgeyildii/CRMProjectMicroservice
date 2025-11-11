package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.ProductOffer;
import com.etiya.catalogservice.service.dtos.requests.productoffer.CreateProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.productoffer.CreatedProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.productoffer.GetListProductOfferResponse;
import com.etiya.common.responses.ProductOfferResponse;

import java.util.List;

public interface ProductOfferService {
    CreatedProductOfferResponse add(CreateProductOfferRequest request);

    List<GetListProductOfferResponse> getAllByCatalogId(int id);

    ProductOfferResponse getById(int id);

    ProductOffer getByEntityId(int id);


}