package com.etiya.salesservice.domain.entities;

import com.etiya.salesservice.domain.enums.OrderStatus;
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

    @Field(name = "status")
    private OrderStatus status=OrderStatus.PENDING;

    @Field(name = "addressId")
    private int addressId;

    private List<OrderItem> orderItems;

    public Order(){
        this.orderItems = new ArrayList<>();
    }

    public Order(String id, int billingAccountId, BigDecimal totalPrice, OrderStatus status, int addressId, List<OrderItem> orderItems) {
        this.id = id;
        this.billingAccountId = billingAccountId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.addressId = addressId;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}