package com.etiya.catalogservice.service.dtos.responses.productspeccharacteristic;

public class CreatedProdSpecCharacteristicResponse {
    private int id;
    private boolean isRequired;
    private int specId;
    private int characteristicId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
