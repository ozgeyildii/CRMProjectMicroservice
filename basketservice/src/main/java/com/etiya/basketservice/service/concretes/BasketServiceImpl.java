package com.etiya.basketservice.service.concretes;

import com.etiya.basketservice.client.CatalogServiceClient;
import com.etiya.basketservice.client.CustomerServiceClient;
import com.etiya.basketservice.domain.Basket;
import com.etiya.basketservice.domain.BasketItem;
import com.etiya.basketservice.repository.BasketRepository;
import com.etiya.basketservice.service.abstracts.BasketService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final CustomerServiceClient customerServiceClient;
    private final CatalogServiceClient catalogServiceClient;

    public BasketServiceImpl(BasketRepository basketRepository, CustomerServiceClient customerServiceClient, CatalogServiceClient catalogServiceClient) {
        this.basketRepository = basketRepository;
        this.customerServiceClient = customerServiceClient;
        this.catalogServiceClient = catalogServiceClient;
    }

    @Override
    public void add(UUID customerId, String productId) {

        var customer = customerServiceClient.getCustomerById(customerId);
        var product = catalogServiceClient.getProductId(productId);
        var basket = basketRepository.getBasketByCustomerId(customer.getId());

        if(basket==null){
            basket = new Basket();
            basket.setCustomerId(customer.getId());
        }

        BasketItem basketItem = new BasketItem();
        basketItem.setProductId(product.getId());
        basketItem.setProductName(product.getName());
        basketItem.setPrice(product.getPrice());
        basket.setCustomerId(customer.getId());
        basket.setTotalPrice(basket.getTotalPrice()+basketItem.getPrice());
        basket.getBasketItems().add(basketItem);
        basketRepository.add(basket);
    }

    @Override
    public Map<String, Basket> getAll() {
        return basketRepository.getAll();
    }
}