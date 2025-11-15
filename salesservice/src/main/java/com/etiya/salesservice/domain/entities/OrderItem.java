package com.etiya.salesservice.domain.entities;

import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OrderItem {

    @Field(name = "id")
    private String id = UUID.randomUUID().toString();;

    @Field(name = "productOfferId")
    private int productOfferId;

    @Field(name = "productOfferName")
    private String productOfferName;

    @Field(name = "price")
    private BigDecimal price;

    @Field(name="discountRate")
    private BigDecimal discountRate;

    @Field(name="discountedPrice")
    private BigDecimal discountedPrice;

    @Field(name="sourceType")
    private String sourceType;

    @Field(name="basketItemId")
    private String basketItemId;

    @Field(name="orderItemCharValues")
    private List<OrderItemCharValue> orderItemCharValues ;

    public OrderItem() {
    }

    public OrderItem(String id, int productOfferId, String productOfferName, BigDecimal price, BigDecimal discountRate, BigDecimal discountedPrice, String sourceType, String basketItemId, List<OrderItemCharValue> orderItemCharValues) {
        this.id = id;
        this.productOfferId = productOfferId;
        this.productOfferName = productOfferName;
        this.price = price;
        this.discountRate = discountRate;
        this.discountedPrice = discountedPrice;
        this.sourceType = sourceType;
        this.basketItemId = basketItemId;
        this.orderItemCharValues = orderItemCharValues;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(int productOfferId) {
        this.productOfferId = productOfferId;
    }

    public String getProductOfferName() {
        return productOfferName;
    }

    public void setProductOfferName(String productOfferName) {
        this.productOfferName = productOfferName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getBasketItemId() {
        return basketItemId;
    }

    public void setBasketItemId(String basketItemId) {
        this.basketItemId = basketItemId;
    }

    public List<OrderItemCharValue> getOrderItemCharValues() {
        return orderItemCharValues;
    }

    public void setOrderItemCharValues(List<OrderItemCharValue> orderItemCharValues) {
        this.orderItemCharValues = orderItemCharValues;
    }
}