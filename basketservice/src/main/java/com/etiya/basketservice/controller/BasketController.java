package com.etiya.basketservice.controller;

import com.etiya.basketservice.domain.Basket;
import com.etiya.basketservice.domain.BasketItem;
import com.etiya.basketservice.service.abstracts.BasketService;
import com.etiya.basketservice.service.dto.request.AddBasketItemRequest;
import com.etiya.basketservice.service.dto.response.CreatedBasketItemResponse;
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


    @PostMapping("/{billingAccountId}/items")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBasketItemResponse addBasketItem(
            @PathVariable int billingAccountId,
            @RequestBody AddBasketItemRequest request) {

        var response = basketService.add(billingAccountId, request);
        return response;
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Basket> getAll(){
        return basketService.getAll();
    }

    @GetMapping("basketId/{basketId}")
    @ResponseStatus(HttpStatus.OK)
    public Basket getBasket(@PathVariable String basketId) {
        return basketService.getBasket(basketId);
    }

    @GetMapping("{billingAccountId}")
    @ResponseStatus(HttpStatus.OK)
    public Basket getByBillingAccount(@PathVariable int billingAccountId) {
        return basketService.getByBillingAccountId(billingAccountId);
    }

    @PostMapping("add-items/{basketId}")
    @ResponseStatus(HttpStatus.OK)
    public Basket addItem(@PathVariable String basketId, @RequestBody BasketItem item) {
        return basketService.addItem(basketId, item);
    }

    @PutMapping("update-items/{basketId}")
    @ResponseStatus(HttpStatus.OK)
    public Basket updateItem(@PathVariable String basketId, @RequestBody BasketItem item) {
        return basketService.updateItem(basketId, item);
    }

    @DeleteMapping("clear/{basketId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearBasket(@PathVariable String basketId) {
        basketService.clearBasket(basketId);
    }

    @DeleteMapping("delete/{billingAccountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBasket(@PathVariable int billingAccountId) {
        basketService.deleteBasket(billingAccountId);
    }
}

