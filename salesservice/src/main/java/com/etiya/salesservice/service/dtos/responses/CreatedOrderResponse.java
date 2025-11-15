package com.etiya.salesservice.service.dtos.responses;

import com.etiya.common.responses.GetAddressResponse;

import java.math.BigDecimal;
import java.util.List;

public class CreatedOrderResponse {

    private String id;

    private int billingAccountId;

    private BigDecimal totalPrice;

    private GetAddressResponse address;

    private List<CreatedOrderItemResponse> createdOrderItem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public GetAddressResponse getAddress() {
        return address;
    }

    public void setAddress(GetAddressResponse address) {
        this.address = address;
    }

    public List<CreatedOrderItemResponse> getCreatedOrderItem() {
        return createdOrderItem;
    }

    public void setCreatedOrderItem(List<CreatedOrderItemResponse> createdOrderItem) {
        this.createdOrderItem = createdOrderItem;
    }
}



