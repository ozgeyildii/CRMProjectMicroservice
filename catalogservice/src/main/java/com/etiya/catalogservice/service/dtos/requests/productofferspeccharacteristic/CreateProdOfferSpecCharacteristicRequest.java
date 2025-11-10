package com.etiya.catalogservice.service.dtos.requests.productofferspeccharacteristic;

public class CreateProdOfferSpecCharacteristicRequest {

    private boolean isRequired;
    private int productOfferSpecId;
    private int characteristicId;

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public int getProductOfferSpecId() {
        return productOfferSpecId;
    }

    public void setProductOfferSpecId(int productOfferSpecId) {
        this.productOfferSpecId = productOfferSpecId;
    }

    public int getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(int characteristicId) {
        this.characteristicId = characteristicId;
    }
}
