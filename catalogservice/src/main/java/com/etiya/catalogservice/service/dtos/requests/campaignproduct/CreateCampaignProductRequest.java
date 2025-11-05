package com.etiya.catalogservice.service.dtos.requests.campaignproduct;

public class CreateCampaignProductRequest {
    private int campaignId;
    private int productId;

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
