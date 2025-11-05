package com.etiya.catalogservice.service.dtos.requests.customeroffer;

import java.time.LocalDate;
import java.util.UUID;

public class CreateCustomerOfferRequest {
    private UUID customerId;
    private LocalDate expirationDate;
    private String status;
    private int productOfferId;

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(int productOfferId) {
        this.productOfferId = productOfferId;
    }
}
