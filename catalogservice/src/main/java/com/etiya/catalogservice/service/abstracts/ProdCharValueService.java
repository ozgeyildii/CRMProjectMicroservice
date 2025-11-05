package com.etiya.catalogservice.service.abstracts;


import com.etiya.catalogservice.service.dtos.requests.productcharvalue.CreateProdCharValueRequest;
import com.etiya.catalogservice.service.dtos.responses.productcharvalue.CreatedProdCharValueResponse;

public interface ProdCharValueService {

    CreatedProdCharValueResponse add(CreateProdCharValueRequest request);
}
