package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CampaignService;
import com.etiya.catalogservice.service.dtos.requests.campaign.CreateCampaignRequest;
import com.etiya.catalogservice.service.dtos.responses.campaign.CreatedCampaignResponse;
import com.etiya.catalogservice.service.dtos.responses.campaign.GetCampaignResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetCampaignResponse> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }
}