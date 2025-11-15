package com.etiya.salesservice.service.dtos.requests;

import com.etiya.salesservice.domain.OrderItem;
import com.etiya.salesservice.domain.enums.OrderStatus;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.UUID;

public class CreateOrderRequest {

    private int billingAccountId;
    private List<CreateOrderItemRequest> items;
    

    public int getBillingAccountId() {
        return billingAccountId;
    }

    public void setBillingAccountId(int billingAccountId) {
        this.billingAccountId = billingAccountId;
    }

    public List<CreateOrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<CreateOrderItemRequest> items) {
        this.items = items;
    }

}
