package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CampaignProductService;
import com.etiya.catalogservice.service.dtos.requests.campaignproductoffer.CreateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.campaignproductoffer.CreatedCampaignProductResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/campaign-products")
public class CampaignProductController {

    private final CampaignProductService campaignProductService;

    public CampaignProductController(CampaignProductService campaignProductService) {
        this.campaignProductService = campaignProductService;
    }

    @PostMapping
    public CreatedCampaignProductResponse add(@RequestBody CreateCampaignProductOfferRequest request) {
         return campaignProductService.add(request);
    }
}
