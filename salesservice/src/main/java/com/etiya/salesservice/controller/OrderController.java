package com.etiya.salesservice.controller;

import com.etiya.salesservice.service.abstracts.OrderService;
import com.etiya.salesservice.service.dtos.requests.CreateOrderRequest;
import com.etiya.salesservice.service.dtos.responses.CreatedOrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedOrderResponse createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.add(request);
    }

    @PostMapping("/create-product/{orderId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void getProductByOrderId(@PathVariable String orderId) {
       orderService.createProduct(orderId);
    }

    @GetMapping("{billingAccountId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CreatedOrderResponse> getProductDetailByBillingAccountId(@PathVariable int billingAccountId) {
     return orderService.getOrdersByBillingAccountId(billingAccountId);
    }
}