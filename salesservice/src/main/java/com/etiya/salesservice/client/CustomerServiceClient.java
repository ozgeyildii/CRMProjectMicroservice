package com.etiya.salesservice.client;

import com.etiya.common.responses.GetAddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customerservice")
public interface CustomerServiceClient {

    @GetMapping("/api/addresses/{id}")
    GetAddressResponse getAddressById(@PathVariable int id);
}
