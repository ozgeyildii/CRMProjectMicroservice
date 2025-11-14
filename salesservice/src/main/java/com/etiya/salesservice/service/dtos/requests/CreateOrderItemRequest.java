package com.etiya.salesservice.service.dtos.requests;

import java.util.List;

public class CreateOrderItemRequest {

    private String basketItemId;  // hangi SEPET itemına ait
    private List<CreateOrderItemCharValueRequest> charValues;


    public String getBasketItemId() {
        return basketItemId;
    }

    public void setBasketItemId(String basketItemId) {
        this.basketItemId = basketItemId;
    }

    public List<CreateOrderItemCharValueRequest> getCharValues() {
        return charValues;
    }

    public void setCharValues(List<CreateOrderItemCharValueRequest> charValues) {
        this.charValues = charValues;
    }
}
