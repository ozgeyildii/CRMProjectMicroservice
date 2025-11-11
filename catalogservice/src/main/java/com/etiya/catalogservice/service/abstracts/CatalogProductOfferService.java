package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.catalogproductoffer.CreateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.catalogproductoffer.CreatedCatalogProductOfferResponse;
import com.etiya.common.responses.CampaignProductOfferResponse;

public interface CatalogProductOfferService {
    CreatedCatalogProductOfferResponse add(CreateCatalogProductOfferRequest request);

    CampaignProductOfferResponse getById(int id);


}
