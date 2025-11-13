package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.campaignproductoffer.CreateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.campaignproductoffer.CreatedCampaignProductResponse;
import com.etiya.common.responses.CampaignProductOfferResponse;

import java.util.List;

public interface CampaignProductOfferService {

    CreatedCampaignProductResponse add(CreateCampaignProductOfferRequest request);

    List<CampaignProductOfferResponse> getById(int id);

}
