package com.etiya.salesservice.service.dtos.responses;

import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class CreatedOrderItemResponse {
    private String id;

    private int productOfferId;

    private String productOfferName;

    private BigDecimal price;

    private BigDecimal discountRate;

    private BigDecimal discountedPrice;

    private String sourceType;

    private List<CreatedOrderItemCharValueResponse> createdOrderItemCharValueResponses;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(int productOfferId) {
        this.productOfferId = productOfferId;
    }

    public String getProductOfferName() {
        return productOfferName;
    }

    public void setProductOfferName(String productOfferName) {
        this.productOfferName = productOfferName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public List<CreatedOrderItemCharValueResponse> getCreatedOrderItemCharValueResponses() {
        return createdOrderItemCharValueResponses;
    }

    public void setCreatedOrderItemCharValueResponses(List<CreatedOrderItemCharValueResponse> createdOrderItemCharValueResponses) {
        this.createdOrderItemCharValueResponses = createdOrderItemCharValueResponses;
    }
}
