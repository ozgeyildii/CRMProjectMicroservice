package com.etiya.catalogservice.service.dtos.requests.productoffercharvalue;

public class CreateProdOfferCharValueRequest {

    private int charValueId;
    private int productOfferId;



    public int getCharValueId() {
        return charValueId;
    }

    public void setCharValueId(int charValueId) {
        this.charValueId = charValueId;
    }

    public int getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(int productOfferId) {
        this.productOfferId = productOfferId;
    }
}
