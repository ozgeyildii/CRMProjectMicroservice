package com.etiya.salesservice.service.dtos.requests;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

public class CreateOrderItemCharValueRequest {

        private String characteristicName;
        private String characteristicValue;

    public String getCharacteristicName() {
        return characteristicName;
    }

    public void setCharacteristicName(String characteristicName) {
        this.characteristicName = characteristicName;
    }

    public String getCharacteristicValue() {
        return characteristicValue;
    }

    public void setCharacteristicValue(String characteristicValue) {
        this.characteristicValue = characteristicValue;
    }
}

