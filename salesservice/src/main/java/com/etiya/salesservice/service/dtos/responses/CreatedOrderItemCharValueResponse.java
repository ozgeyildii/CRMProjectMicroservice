package com.etiya.salesservice.service.dtos.responses;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

public class CreatedOrderItemCharValueResponse {

    private String id;
    private String orderItemId;       // Hangi OrderItem'a ait?
    private String characteristicName;  // "Speed", "ModemBrand", "Username"
    private String characteristicValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

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
