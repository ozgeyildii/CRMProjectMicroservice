package com.etiya.salesservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "orders")
public class Order {

    @MongoId
    @Field(name = "id")
    private String id= UUID.randomUUID().toString();

    @Field(name = "billingAccountId")
    private int billingAccountId;

    @Field(name = "totalPrice")
    private BigDecimal totalPrice;

    //TODO: Müşteri bilgileri => FirstName,LastName,Email

    private List<OrderItem> orderItems;

    public Order(){
        this.orderItems = new ArrayList<>();
    }

    public Order(String id, int billingAccountId, BigDecimal totalPrice, List<OrderItem> orderItems) {
        this.id = id;
        this.billingAccountId = billingAccountId;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBillingAccountId() {
        return billingAccountId;
    }

    public void setBillingAccountId(int billingAccountId) {
        this.billingAccountId = billingAccountId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}