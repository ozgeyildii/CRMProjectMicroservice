package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CatalogService;
import com.etiya.catalogservice.service.dtos.requests.catalog.CreateCatalogRequest;
import com.etiya.catalogservice.service.dtos.responses.campaign.GetCampaignResponse;
import com.etiya.catalogservice.service.dtos.responses.catalog.CreatedCatalogResponse;
import com.etiya.catalogservice.service.dtos.responses.catalog.GetListCatalogResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogs")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @PostMapping
    public CreatedCatalogResponse createCatalog(@RequestBody CreateCatalogRequest request) {
        return catalogService.createCatalog(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListCatalogResponse> getAllCampaigns() {
        return catalogService.getAll();
    }
}
