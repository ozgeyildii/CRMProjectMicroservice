package com.etiya.basketservice.service.concretes;

import com.etiya.basketservice.client.CatalogServiceClient;
import com.etiya.basketservice.client.CustomerServiceClient;
import com.etiya.basketservice.domain.Basket;
import com.etiya.basketservice.domain.BasketItem;
import com.etiya.basketservice.repository.BasketRepository;
import com.etiya.basketservice.service.abstracts.BasketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final CustomerServiceClient customerServiceClient;
    private final CatalogServiceClient catalogServiceClient;

    public BasketServiceImpl(BasketRepository basketRepository,
                             CustomerServiceClient customerServiceClient,
                             CatalogServiceClient catalogServiceClient) {
        this.basketRepository = basketRepository;
        this.customerServiceClient = customerServiceClient;
        this.catalogServiceClient = catalogServiceClient;
    }

    @Override
    public void add(int billingAccountId, String productId) {

        var billingAccount = customerServiceClient.getBillingAccountById(billingAccountId);
        if (billingAccount == null ) {
            throw new RuntimeException("Billing account not found.");
        }

        var product = catalogServiceClient.getProductId(productId);
        var basket = basketRepository.getBasketByBillingAccountId(billingAccount.getId());

        if (basket == null) {
            basket = new Basket();
            basket.setBillingAccountId(billingAccount.getId());
        }
        Optional<BasketItem> existingItem = basket.getBasketItems().stream()
                .filter(i -> i.getProductId()==(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            BasketItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + 1);
        } else {
            BasketItem basketItem = new BasketItem();
            basketItem.setProductId(product.getId());
            basketItem.setProductName(product.getName());
            basketItem.setPrice(product.getPrice());
            basketItem.setQuantity(1);
            basket.getBasketItems().add(basketItem);
        }

        updateTotal(basket);
        basketRepository.add(basket);
    }

    @Override
    public Map<String, Basket> getAll() {
        return basketRepository.getAll();
    }


    @Override
    public Basket getBasket(String basketId) {
        return basketRepository.getAll().values().stream()
                .filter(basket -> basket.getId().equals(basketId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Basket not found with id: " + basketId));
    }

    @Override
    public Basket addItem(String basketId, BasketItem item) {
        Basket basket = getBasket(basketId);
        basket.getBasketItems().add(item);
        updateTotal(basket);
        basketRepository.add(basket);
        return basket;
    }

    @Override
    public Basket updateItem(String basketId, BasketItem item) {
        Basket basket = getBasket(basketId);
        List<BasketItem> items = basket.getBasketItems();
        boolean updated = false;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(item.getId())) {
                items.set(i, item);
                updated = true;
                break;
            }
        }

        if (!updated) {
            throw new RuntimeException("Item not found in basket: " + item.getId());
        }

        updateTotal(basket);
        basketRepository.add(basket);
        return basket;
    }


    @Override
    public void clearBasket(String basketId) {
        Basket basket = getBasket(basketId);
        basket.getBasketItems().clear();
        basket.setTotalPrice(0);
        basketRepository.add(basket);
    }

    @Override
    public void deleteBasket(int billingAccountId) {
        basketRepository.deleteBasketByBillingAccountId(billingAccountId);
    }

    private void updateTotal(Basket basket) {
        double total = basket.getBasketItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        basket.setTotalPrice(total);
    }

    @Override
    public Basket getByBillingAccountId(int billingAccountId) {
        Basket basket = basketRepository.getBasketByBillingAccountId(billingAccountId);
        if (basket == null) {
            throw new RuntimeException("Basket not found for billing account id: " + billingAccountId);
        }
        return basket;
    }

}
