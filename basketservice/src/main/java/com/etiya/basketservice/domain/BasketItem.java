package com.etiya.basketservice.domain;

import java.io.Serializable;
import java.util.UUID;

public class BasketItem implements Serializable {

    private String id;
    private String basketId;
    private int productOfferId;
    private String productOfferName;
    private int campaignProductOfferId;
    private int catalogProductOfferId;
    private Double price;
    private int quantity;
    private double discountedPrice;
    private double discountRate;

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

    public int getCampaignProductOfferId() {
        return campaignProductOfferId;
    }

    public void setCampaignProductOfferId(int campaignProductOfferId) {
        this.campaignProductOfferId = campaignProductOfferId;
    }

    public int getCatalogProductOfferId() {
        return catalogProductOfferId;
    }

    public void setCatalogProductOfferId(int catalogProductOfferId) {
        this.catalogProductOfferId = catalogProductOfferId;
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

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public BasketItem() {
    }

    public BasketItem(String id, String basketId, int productOfferId, String productOfferName, int campaignProductOfferId, int catalogProductOfferId, Double price, int quantity, double discountedPrice, double discountRate) {
        this.id = id;
        this.basketId = basketId;
        this.productOfferId = productOfferId;
        this.productOfferName = productOfferName;
        this.campaignProductOfferId = campaignProductOfferId;
        this.catalogProductOfferId = catalogProductOfferId;
        this.price = price;
        this.quantity = quantity;
        this.discountedPrice = discountedPrice;
        this.discountRate = discountRate;
    }
}
