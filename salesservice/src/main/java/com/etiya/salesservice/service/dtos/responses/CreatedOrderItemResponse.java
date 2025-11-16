package com.etiya.salesservice.service.dtos.responses;

import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class CreatedOrderItemResponse {
    private String id;

    private int productOfferId;

    private String productOfferName;

    private int productId;

    private String productName;

    private Integer campaignId;

    private String campaignName;

    private BigDecimal price;

    private BigDecimal discountRate;

    private BigDecimal discountedPrice;

    private String sourceType;

    private String basketItemId;

    private List<CreatedOrderItemCharValueResponse> createdOrderItemCharValues;


    public String getId() {
        return id;
    }

    public String getBasketItemId() {
        return basketItemId;
    }

    public void setBasketItemId(String basketItemId) {
        this.basketItemId = basketItemId;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public List<CreatedOrderItemCharValueResponse> getCreatedOrderItemCharValues() {
        return createdOrderItemCharValues;
    }

    public void setCreatedOrderItemCharValues(List<CreatedOrderItemCharValueResponse> createdOrderItemCharValues) {
        this.createdOrderItemCharValues = createdOrderItemCharValues;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
