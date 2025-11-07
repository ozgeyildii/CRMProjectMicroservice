package com.etiya.salesservice.domain;

import org.springframework.data.mongodb.core.mapping.Field;

public class OrderItem {

    @Field(name = "id")
    private String id;

    @Field(name = "productId")
    private String productId;

    @Field(name = "productName")
    private String productName;

    @Field(name = "price")
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
}