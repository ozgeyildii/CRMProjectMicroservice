package com.etiya.basketservice.service.dto.request;

public class AddBasketItemRequest {
    private String type; // "OFFER" or "CAMPAIGN"
    private int id;      // productOfferId or campaignProductOfferId

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }


}
