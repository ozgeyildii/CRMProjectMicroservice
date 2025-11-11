package com.etiya.common.responses;

public class CampaignProductOfferResponse {
    private int id;                   // CampaignProductOffer.id
    private int productOfferId;       // ProductOffer.id
    private String productOfferName;  // ProductOffer.name
    private double basePrice;         // Product.price
    private double discountRate;      // Campaign.discountRate
    private double discountedPrice;   // basePrice * (1 - discountRate)
    private int campaignId;
    private String campaignName;
    private String campaignCode;

    public CampaignProductOfferResponse() {
    }

    public CampaignProductOfferResponse(int id, int productOfferId, String productOfferName,
                                        double basePrice, double discountRate, double discountedPrice,
                                        int campaignId, String campaignName, String campaignCode) {
        this.id = id;
        this.productOfferId = productOfferId;
        this.productOfferName = productOfferName;
        this.basePrice = basePrice;
        this.discountRate = discountRate;
        this.discountedPrice = discountedPrice;
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

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
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