package com.etiya.salesservice.service.abstracts;

import com.etiya.salesservice.service.dtos.requests.CreateOrderRequest;
import com.etiya.salesservice.service.dtos.responses.CreatedOrderResponse;

public interface OrderService {
    CreatedOrderResponse add(CreateOrderRequest createOrderRequest);  //TODO:customerId alanı billingAccountId olacak


}
