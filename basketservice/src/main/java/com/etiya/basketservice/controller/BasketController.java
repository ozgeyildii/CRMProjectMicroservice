package com.etiya.basketservice.controller;

import com.etiya.basketservice.domain.Basket;
import com.etiya.basketservice.service.abstracts.BasketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/baskets")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestParam UUID customerId,@RequestParam String productId){
        basketService.add(customerId,productId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Basket> getAll(){
        return basketService.getAll();
    }
}