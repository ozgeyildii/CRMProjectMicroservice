package com.etiya.salesservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "orders")
public class Order {

    @MongoId
    @Field(name = "id")
    private String id;

    @Field(name = "customerId")
    private String customerId;

    @Field(name = "totalPrice")
    private double totalPrice;

    //TODO: Müşteri bilgileri => FirstName,LastName,Email

    private List<OrderItem> orderItems;

    public Order(){
        this.orderItems = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}