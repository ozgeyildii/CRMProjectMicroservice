package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.campaignproductoffer.CreateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.campaignproductoffer.CreatedCampaignProductResponse;

public interface CampaignProductService {
    CreatedCampaignProductResponse add(CreateCampaignProductOfferRequest request);

}
