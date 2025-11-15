package com.etiya.catalogservice.transport.kafka.consumer;

import com.etiya.catalogservice.domain.Product;
import com.etiya.catalogservice.domain.ProductOffer;
import com.etiya.catalogservice.service.abstracts.ProductOfferService;
import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.common.events.billingaccount.CreateBillingAccountEvent;
import com.etiya.common.events.product.CreateProductEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class CreatedProductConsumer {

    private final ProductService productService;
    private final ProductOfferService productOfferService;
    private final Logger LOGGER = LoggerFactory.getLogger(CreatedProductConsumer.class);

    public CreatedProductConsumer(ProductService productService,  ProductOfferService productOfferService) {
        this.productService = productService;
        this.productOfferService = productOfferService;
    }

    @Bean
    public Consumer<CreateProductEvent> productCreated() {

        return event -> {

            ProductOffer productOffer= productOfferService.getByEntityId(event.product_offer_id());

            Product product = new Product(
                    event.name(),
                    event.price(),
                    event.billingAccountId(),
                    productOffer
            );

            productService.addProductByEntity(product);
            LOGGER.info(String.format("Consumed Product (created)=> %s", event));
        };
    }
}