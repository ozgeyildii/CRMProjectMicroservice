package com.etiya.catalogservice.service.dtos.responses.product;

public class GetProductDetailResponse {
    private int productId;
    private String productName;
    private Integer productOfferId;
    private String productOfferName;
    private Integer campaignId;
    private String campaignName;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(Integer productOfferId) {
        this.productOfferId = productOfferId;
    }

    public String getProductOfferName() {
        return productOfferName;
    }

    public void setProductOfferName(String productOfferName) {
        this.productOfferName = productOfferName;
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

    public GetProductDetailResponse() {
    }

    public GetProductDetailResponse(int productId, String productName, Integer productOfferId, String productOfferName, Integer campaignId, String campaignName) {
        this.productId = productId;
        this.productName = productName;
        this.productOfferId = productOfferId;
        this.productOfferName = productOfferName;
        this.campaignId = campaignId;
        this.campaignName = campaignName;
    }
}