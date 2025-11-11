package com.etiya.basketservice.service.abstracts;

import com.etiya.basketservice.domain.Basket;
import com.etiya.basketservice.domain.BasketItem;
import com.etiya.basketservice.service.dto.request.AddBasketItemRequest;
import com.etiya.basketservice.service.dto.response.CreatedBasketItemResponse;

import java.util.Map;

public interface BasketService {
    CreatedBasketItemResponse add(int billingAccountId, AddBasketItemRequest basketItem);
    Map<String, Basket> getAll();
    Basket getBasket(String basketId);
    Basket addItem(String basketId, BasketItem item);
    Basket updateItem(String basketId, BasketItem item);
    void clearBasket(String basketId);
    void deleteBasket(int basketId);
    Basket getByBillingAccountId(int billingAccountId);



}