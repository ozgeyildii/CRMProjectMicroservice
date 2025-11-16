package com.etiya.salesservice.service.abstracts;

import com.etiya.salesservice.service.dtos.requests.CreateOrderRequest;
import com.etiya.salesservice.service.dtos.responses.CreatedOrderResponse;

import java.util.List;

public interface OrderService {
    CreatedOrderResponse add(CreateOrderRequest createOrderRequest);
    void createProduct(String orderId);
    void updateProductInfo(String orderItemId,
                                  int productId,
                                  String productName,
                                  Integer campaignId,
                                  String campaignName);
    List<CreatedOrderResponse> getOrdersByBillingAccountId(int billingAccountId);
}
