package com.etiya.basketservice.client;

import com.etiya.common.responses.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalogservice")
public interface CatalogServiceClient {

    @GetMapping("/api/products/{id}")
    ProductResponse getProductId(@PathVariable("id") String id);
}