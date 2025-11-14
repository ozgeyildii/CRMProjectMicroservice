package com.etiya.common.responses;

import java.math.BigDecimal;
import java.util.List;

public class GetBasketResponse {

    private String basketId;
    private int billingAccId;
    private BigDecimal totalPrice;
    private List<GetBasketItemResponse> basketItems;

    public int getBillingAccId() {
        return billingAccId;
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public void setBillingAccId(int billingAccId) {
        this.billingAccId = billingAccId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<GetBasketItemResponse> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<GetBasketItemResponse> basketItems) {
        this.basketItems = basketItems;
    }
}
