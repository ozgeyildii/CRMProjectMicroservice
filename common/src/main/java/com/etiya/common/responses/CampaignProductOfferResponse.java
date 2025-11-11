package com.etiya.common.responses;

import java.math.BigDecimal;

public class CampaignProductOfferResponse {
    private int id;                   // CampaignProductOffer.id
    private int productOfferId;       // ProductOffer.id
    private String productOfferName;  // ProductOffer.name
    private BigDecimal price;         // Product.price
    private int stock;
    private BigDecimal discountRate;      // Campaign.discountRate
    private int campaignId;
    private String campaignName;
    private String campaignCode;

    public CampaignProductOfferResponse() {
    }

    public CampaignProductOfferResponse(int id, int productOfferId, String productOfferName, BigDecimal price, int stock, BigDecimal discountRate, int campaignId, String campaignName, String campaignCode) {
        this.id = id;
        this.productOfferId = productOfferId;
        this.productOfferName = productOfferName;
        this.price = price;
        this.stock = stock;
        this.discountRate = discountRate;
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.campaignCode = campaignCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }
}