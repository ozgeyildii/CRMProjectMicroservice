package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.catalogproductoffer.CreateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.catalogproductoffer.CreatedCatalogProductOfferResponse;
import com.etiya.common.responses.GetCatalogRelByProductOfferId;

public interface CatalogProductOfferService {
    CreatedCatalogProductOfferResponse add(CreateCatalogProductOfferRequest request);
    GetCatalogRelByProductOfferId getByProductOfferId(int productOfferId);

}
