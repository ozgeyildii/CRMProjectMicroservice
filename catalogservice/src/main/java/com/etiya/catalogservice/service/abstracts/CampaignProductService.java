package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.campaignproduct.CreateCampaignProductRequest;
import com.etiya.catalogservice.service.dtos.responses.campaignproduct.CreatedCampaignProductResponse;

public interface CampaignProductService {
    CreatedCampaignProductResponse add(CreateCampaignProductRequest request);

}
