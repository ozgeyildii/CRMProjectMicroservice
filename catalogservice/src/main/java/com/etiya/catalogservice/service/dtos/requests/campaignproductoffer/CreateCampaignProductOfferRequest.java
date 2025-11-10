package com.etiya.catalogservice.service.dtos.requests.campaignproductoffer;

public class CreateCampaignProductOfferRequest {
    private int campaignId;
    private int productOfferId;

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public int getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(int productOfferId) {
        this.productOfferId = productOfferId;
    }
}
