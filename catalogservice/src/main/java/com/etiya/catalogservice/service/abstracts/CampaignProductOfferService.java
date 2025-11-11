package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.campaignproductoffer.CreateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.campaignproductoffer.CreatedCampaignProductResponse;
import com.etiya.common.responses.CampaignProductOfferResponse;

public interface CampaignProductOfferService {

    CreatedCampaignProductResponse add(CreateCampaignProductOfferRequest request);

    CampaignProductOfferResponse getById(int id);

}
