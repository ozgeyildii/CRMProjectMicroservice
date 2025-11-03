package com.etiya.basketservice.domain;

import java.io.Serializable;
import java.util.UUID;

public class BasketItem implements Serializable {

    private String id;
    private String productId;
    private String productName;
    private double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BasketItem(){
        this.id = UUID.randomUUID().toString();
    }


}