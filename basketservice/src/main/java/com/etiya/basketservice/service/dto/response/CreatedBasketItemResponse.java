package com.etiya.basketservice.service.dto.response;

import java.math.BigDecimal;

public class CreatedBasketItemResponse {
    private String basketId;
    private String basketItemId;
    private int productOfferId;
    private String productOfferName;
    private BigDecimal price;
    private int campaignProductOfferId;
    private int catalogProductOfferId;
    private BigDecimal discountedPrice;
    private BigDecimal discountRate;
    private String type;  // "OFFER" veya "CAMPAIGN"

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public String getBasketItemId() {
        return basketItemId;
    }

    public void setBasketItemId(String basketItemId) {
        this.basketItemId = basketItemId;
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

    public int getCampaignProductOfferId() {
        return campaignProductOfferId;
    }

    public void setCampaignProductOfferId(int campaignProductOfferId) {
        this.campaignProductOfferId = campaignProductOfferId;
    }

    public int getCatalogProductOfferId() {
        return catalogProductOfferId;
    }

    public void setCatalogProductOfferId(int catalogProductOfferId) {
        this.catalogProductOfferId = catalogProductOfferId;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
