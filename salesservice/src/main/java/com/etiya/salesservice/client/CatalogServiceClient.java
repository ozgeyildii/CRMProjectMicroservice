package com.etiya.salesservice.client;

import org.springframework.cloud.openfeign.FeignClient;


import java.util.List;

@FeignClient(name="catalogservice")

public interface CatalogServiceClient {
/*
    @GetMapping("/api/products/{id}")
    List<GetProductDetailResponse> getById(@PathVariable int id);*/
}
