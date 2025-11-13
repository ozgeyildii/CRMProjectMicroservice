package com.etiya.basketservice.client;

import com.etiya.common.responses.CampaignProductOfferResponse;
import com.etiya.common.responses.GetCatalogRelByProductOfferId;
import com.etiya.common.responses.ProductOfferResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "catalogservice")
public interface CatalogServiceClient {

    @GetMapping("/api/products/{id}")
    ProductOfferResponse getProductId(@PathVariable("id") String id);

    @GetMapping("/api/product-offers/{id}")
    ProductOfferResponse getProductOfferById(@PathVariable("id") int id);

    @GetMapping("/api/campaign-product-offers/{id}")
    List<CampaignProductOfferResponse> getCampaignProductOfferById(@PathVariable("id") int id);

    @GetMapping("/api/catalog-product-offers/{productOfferId}")
    GetCatalogRelByProductOfferId getByProductOfferId(@PathVariable("productOfferId") int productOfferId);
}