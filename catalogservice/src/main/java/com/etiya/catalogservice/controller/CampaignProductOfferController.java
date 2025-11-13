package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CampaignProductOfferService;
import com.etiya.catalogservice.service.dtos.requests.campaignproductoffer.CreateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.campaignproductoffer.CreatedCampaignProductResponse;
import com.etiya.common.responses.CampaignProductOfferResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaign-product-offers")
public class CampaignProductOfferController {

    private final CampaignProductOfferService campaignProductOfferService;

    public CampaignProductOfferController(CampaignProductOfferService campaignProductOfferService) {
        this.campaignProductOfferService = campaignProductOfferService;
    }

    @PostMapping
    public CreatedCampaignProductResponse add(@RequestBody CreateCampaignProductOfferRequest request) {
         return campaignProductOfferService.add(request);
    }

    @GetMapping("/{id}")
    public List<CampaignProductOfferResponse> getById(@PathVariable int id) {
        return campaignProductOfferService.getById(id);
    }
}

