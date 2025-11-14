package com.etiya.salesservice.service.dtos.responses;

import com.etiya.salesservice.domain.OrderItem;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class CreatedOrderResponse {

    private String id;

    private int billingAccountId;

    private BigDecimal totalPrice;


    private List<CreatedOrderItemResponse> createdOrderItemResponses;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CreatedOrderItemResponse> getCreatedOrderItemResponses() {
        return createdOrderItemResponses;
    }

    public void setCreatedOrderItemResponses(List<CreatedOrderItemResponse> createdOrderItemResponses) {
        this.createdOrderItemResponses = createdOrderItemResponses;
    }

    public int getBillingAccountId() {
        return billingAccountId;
    }

    public void setBillingAccountId(int billingAccountId) {
        this.billingAccountId = billingAccountId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}



