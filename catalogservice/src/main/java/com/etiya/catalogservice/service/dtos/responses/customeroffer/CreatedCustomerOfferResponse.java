package com.etiya.catalogservice.service.dtos.responses.customeroffer;

import java.time.LocalDate;
import java.util.UUID;

public class CreatedCustomerOfferResponse {
    private int id;
    private UUID customerId;
    private LocalDate expirationDate;
    private String status;
    private int productOfferId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
