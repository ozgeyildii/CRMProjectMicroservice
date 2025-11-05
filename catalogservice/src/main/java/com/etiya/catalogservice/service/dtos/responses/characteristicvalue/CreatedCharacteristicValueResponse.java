package com.etiya.catalogservice.service.dtos.responses.characteristicvalue;

public class CreatedCharacteristicValueResponse {

    private int id;
    private String value;
    private int charId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
