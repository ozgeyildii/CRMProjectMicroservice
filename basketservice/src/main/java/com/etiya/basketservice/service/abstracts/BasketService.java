package com.etiya.basketservice.service.abstracts;

import com.etiya.basketservice.domain.Basket;
import com.etiya.basketservice.domain.BasketItem;

import java.util.Map;
import java.util.UUID;

public interface BasketService {
    void add(int billingAccountId,String productId);
    Map<String, Basket> getAll();
    Basket getBasket(String basketId);
    Basket addItem(String basketId, BasketItem item);
    Basket updateItem(String basketId, BasketItem item);
    void clearBasket(String basketId);
    void deleteBasket(int basketId);
    Basket getByBillingAccountId(int billingAccountId);



}