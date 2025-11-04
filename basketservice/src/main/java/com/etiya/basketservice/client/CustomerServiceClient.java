package com.etiya.basketservice.client;

import com.etiya.common.responses.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.UUID;

@FeignClient(name = "customerservice")
public interface CustomerServiceClient {

    @GetMapping("/api/individual-customers/{id}")
    CustomerResponse getCustomerById(@PathVariable("id")UUID id);
}