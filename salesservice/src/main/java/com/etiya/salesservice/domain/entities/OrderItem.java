package com.etiya.salesservice.domain.entities;

import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OrderItem {

    @Field(name = "id")
    private String id = UUID.randomUUID().toString();

    @Field(name="ProductId")
    private int productId;

    @Field(name="ProductName")
    private String productName;

    @Field(name="CampaignId")
    private Integer campaignId;

    @Field(name="CampaignName")
    private String campaignName;

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

    public OrderItem(String id, int productId, String productName, Integer campaignId, String campaignName, int productOfferId, String productOfferName, BigDecimal price, BigDecimal discountRate, BigDecimal discountedPrice, String sourceType, String basketItemId, List<OrderItemCharValue> orderItemCharValues) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.productOfferId = productOfferId;
        this.productOfferName = productOfferName;
        this.price = price;
        this.discountRate = discountRate;
        this.discountedPrice = discountedPrice;
        this.sourceType = sourceType;
        this.basketItemId = basketItemId;
        this.orderItemCharValues = orderItemCharValues;
    }

    public OrderItem(String id, int productId, String productName, Integer campaignId, String campaignName) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.campaignId = campaignId;
        this.campaignName = campaignName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}