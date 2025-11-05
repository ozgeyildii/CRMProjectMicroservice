package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.productspec.CreateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.responses.productspec.CreatedProductSpecificationResponse;

public interface ProductSpecificationService {
    CreatedProductSpecificationResponse add(CreateProductSpecificationRequest request);

}
