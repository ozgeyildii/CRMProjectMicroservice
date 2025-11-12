package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.campaign.CreateCampaignRequest;
import com.etiya.catalogservice.service.dtos.responses.campaign.CreatedCampaignResponse;
import com.etiya.catalogservice.service.dtos.responses.campaign.GetCampaignResponse;

import java.util.List;

public interface CampaignService {
    CreatedCampaignResponse add(CreateCampaignRequest request);
    List<GetCampaignResponse> getAllCampaigns();
}
