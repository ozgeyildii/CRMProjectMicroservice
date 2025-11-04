package com.etiya.basketservice.service.abstracts;

import com.etiya.basketservice.domain.Basket;

import java.util.Map;
import java.util.UUID;

public interface BasketService {
    void add(UUID customerId,String productId);
    Map<String, Basket> getAll();

}