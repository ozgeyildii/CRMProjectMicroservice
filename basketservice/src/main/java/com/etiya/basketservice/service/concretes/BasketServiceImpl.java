package com.etiya.basketservice.service.concretes;

import com.etiya.basketservice.client.CatalogServiceClient;
import com.etiya.basketservice.client.CustomerServiceClient;
import com.etiya.basketservice.domain.Basket;
import com.etiya.basketservice.domain.BasketItem;
import com.etiya.basketservice.repository.BasketRepository;
import com.etiya.basketservice.service.abstracts.BasketService;
import com.etiya.basketservice.service.dto.request.AddBasketItemRequest;
import com.etiya.basketservice.service.dto.response.CreatedBasketItemResponse;
import com.etiya.basketservice.service.mapping.BasketMapper;
import com.etiya.common.responses.CampaignProductOfferResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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
    public CreatedBasketItemResponse add(int billingAccountId, AddBasketItemRequest request) {

        var billingAccount = customerServiceClient.getBillingAccountById(billingAccountId);
        if (billingAccount == null) {
            throw new RuntimeException("Billing account not found.");
        }

        // 🧺 Mevcut sepeti getir
        var basket = basketRepository.getBasketByBillingAccountId(billingAccount.getId());
        if (basket == null) {
            basket = new Basket(); // UUID constructor’da üretiliyor
            basket.setBillingAccountId(billingAccount.getId());
            basket.setBasketItems(new ArrayList<>());
            basket.setTotalPrice(BigDecimal.ZERO);
        }

        BasketItem basketItem ;
        if ("CAMPAIGN".equalsIgnoreCase(request.getType())) {

            // 1) Kampanya offer listesini getir
            List<CampaignProductOfferResponse> campaignOffers =
                    catalogServiceClient.getCampaignProductOfferById(request.getId());

            // 2) FE’nin seçtiği ID’ye karşılık gelen satırı bul
            var selectedOffer = campaignOffers.stream()
                    .filter(o -> o.getId() == request.getId())
                    .findFirst()
                    .orElseThrow(() ->
                            new RuntimeException("Campaign offer not found: " + request.getId()));

            // 3) Mapper’a TEK elemanı gönder
            basketItem = BasketMapper.INSTANCE.fromCampaignProductOfferResponse(selectedOffer);

        } else {
            var productOffer = catalogServiceClient.getProductOfferById(request.getId());
            var catalogOfferRel = catalogServiceClient.getByProductOfferId(request.getId());
            basketItem = BasketMapper.INSTANCE.fromProductOfferResponse(productOffer);
            basketItem.setCatalogProductOfferId(catalogOfferRel.getId());

        }

        // 🔗 Sepet ile ilişkilendir
        basketItem.setBasketId(basket.getId());

        basketItem.setDiscountedPrice(calcDiscountedPrice(
                basketItem.getPrice(),
                basketItem.getDiscountRate()
        ));

        // quantity sabit: 1
        basketItem.setQuantity(1);

        // Sepete ekle
        basket.getBasketItems().add(basketItem);

        updateTotal(basket);

        basketRepository.add(basket);


        return BasketMapper.INSTANCE.toCreatedBasketItemResponse(basketItem);
    }

    private BigDecimal calcDiscountedPrice(BigDecimal price, BigDecimal discountRate) {

        if (price == null) price = BigDecimal.ZERO;

        if (discountRate == null) discountRate = BigDecimal.ZERO;

        BigDecimal rate = discountRate.compareTo(BigDecimal.ONE) > 0

                ? discountRate.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)

                : discountRate;

        return price.multiply(BigDecimal.ONE.subtract(rate))

                .setScale(2, RoundingMode.HALF_UP);

    }


    private void updateTotal(Basket basket) {
        BigDecimal total = basket.getBasketItems().stream()
                .map(item -> item.getDiscountedPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        basket.setTotalPrice(total);
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
        basket.setTotalPrice(BigDecimal.valueOf(0));
        basketRepository.add(basket);
    }

    @Override
    public void deleteBasket(int billingAccountId) {
        basketRepository.deleteBasketByBillingAccountId(billingAccountId);
    }


    @Override
    public Basket getByBillingAccountId(int billingAccountId) {
        Basket basket = basketRepository.getBasketByBillingAccountId(billingAccountId);
        if (basket == null) {
            Basket empty = new Basket();
            empty.setId("");   // FE bunu “henüz gerçek basket oluşmamış” olarak görür
            empty.setBillingAccountId(billingAccountId);
            empty.setTotalPrice(BigDecimal.ZERO);
            empty.setBasketItems(new ArrayList<>());
            return empty;
        }

        return basket;

    }

}
