package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.productofferspec.CreateProductOfferSpecificationRequest;
import com.etiya.catalogservice.service.dtos.responses.productspec.CreatedProductSpecificationResponse;

public interface ProductSpecificationService {
    CreatedProductSpecificationResponse add(CreateProductOfferSpecificationRequest request);

}
