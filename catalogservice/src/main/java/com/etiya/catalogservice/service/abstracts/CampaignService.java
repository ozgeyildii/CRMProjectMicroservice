package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.campaign.CreateCampaignRequest;
import com.etiya.catalogservice.service.dtos.responses.campaign.CreatedCampaignResponse;

public interface CampaignService {
    CreatedCampaignResponse add(CreateCampaignRequest request);

}
