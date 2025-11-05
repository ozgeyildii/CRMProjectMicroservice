package com.etiya.basketservice.domain;

import java.io.Serializable;
import java.util.UUID;

public class BasketItem implements Serializable {

    private String id;
    private String basketId;
    private int productId;
    private String productName;
    private Double price;
    private int quantity;
    private  double discountedPrice;
    private double discountRate;

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public BasketItem(){
        this.id = UUID.randomUUID().toString();
    }


}