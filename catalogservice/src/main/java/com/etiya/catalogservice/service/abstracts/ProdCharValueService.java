package com.etiya.catalogservice.service.abstracts;


import com.etiya.catalogservice.service.dtos.requests.productoffercharvalue.CreateProdOfferCharValueRequest;
import com.etiya.catalogservice.service.dtos.responses.productcharvalue.CreatedProdCharValueResponse;

public interface ProdCharValueService {

    CreatedProdCharValueResponse add(CreateProdOfferCharValueRequest request);
}
