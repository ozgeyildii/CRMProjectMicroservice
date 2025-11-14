package com.etiya.salesservice.domain;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

public class OrderItemCharValue {

    @Field(name = "id")
    private String id = UUID.randomUUID().toString();

    @Field(name="orderItemId")
    private String orderItemId;         // Hangi OrderItem'a ait?

    @Field(name="characteristicName")
    private String characteristicName;  // "Speed", "ModemBrand", "Username"
    @Field(name = "characteristicValue")
    private String characteristicValue;

    public OrderItemCharValue() {
    }

    public OrderItemCharValue(String id, String orderItemId, String characteristicName, String characteristicValue) {
        this.id = id;
        this.orderItemId = orderItemId;
        this.characteristicName = characteristicName;
        this.characteristicValue = characteristicValue;
    }

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
