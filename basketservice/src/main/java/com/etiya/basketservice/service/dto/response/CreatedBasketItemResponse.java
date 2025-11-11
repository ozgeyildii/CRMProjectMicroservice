package com.etiya.basketservice.service.dto.response;

import java.math.BigDecimal;

public class CreatedBasketItemResponse {
    private String basketId;
    private String basketItemId;
    private String productOfferName;
    private BigDecimal price;
    private BigDecimal discountedPrice;
    private BigDecimal discountRate;
    private String type;  // "OFFER" veya "CAMPAIGN"

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public String getBasketItemId() {
        return basketItemId;
    }

    public void setBasketItemId(String basketItemId) {
        this.basketItemId = basketItemId;
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

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
