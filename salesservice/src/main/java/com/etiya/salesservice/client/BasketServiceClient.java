package com.etiya.salesservice.client;

import com.etiya.common.responses.CampaignProductOfferResponse;
import com.etiya.common.responses.GetBasketResponse;
import com.etiya.common.responses.GetCatalogRelByProductOfferId;
import com.etiya.common.responses.ProductOfferResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "basketservice")
public interface BasketServiceClient {

    @GetMapping("/api/baskets/get-basket/{billingAccountId}")
    GetBasketResponse getBasketByBillingAccount(@PathVariable int billingAccountId);
}
