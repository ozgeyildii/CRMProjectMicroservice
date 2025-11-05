package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CampaignService;
import com.etiya.catalogservice.service.dtos.requests.campaign.CreateCampaignRequest;
import com.etiya.catalogservice.service.dtos.responses.campaign.CreatedCampaignResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping
    public CreatedCampaignResponse add(@RequestBody CreateCampaignRequest request) {

        return campaignService.add(request);

    }
}