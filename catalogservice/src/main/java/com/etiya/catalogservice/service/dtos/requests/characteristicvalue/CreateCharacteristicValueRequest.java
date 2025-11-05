package com.etiya.catalogservice.service.dtos.requests.characteristicvalue;

public class CreateCharacteristicValueRequest {

    private String value;
    private int charId;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCharId() {
        return charId;
    }

    public void setCharId(int charId) {
        this.charId = charId;
    }
}
