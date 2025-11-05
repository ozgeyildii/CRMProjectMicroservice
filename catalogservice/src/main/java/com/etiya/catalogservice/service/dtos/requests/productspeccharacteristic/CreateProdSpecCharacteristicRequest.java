package com.etiya.catalogservice.service.dtos.requests.productspeccharacteristic;

public class CreateProdSpecCharacteristicRequest {

    private boolean isRequired;
    private int specId;
    private int characteristicId;

    public boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    public int getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(int characteristicId) {
        this.characteristicId = characteristicId;
    }
}
