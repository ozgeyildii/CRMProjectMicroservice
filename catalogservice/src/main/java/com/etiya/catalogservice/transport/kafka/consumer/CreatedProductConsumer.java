package com.etiya.catalogservice.transport.kafka.consumer;

import com.etiya.catalogservice.domain.Product;
import com.etiya.catalogservice.domain.ProductOffer;
import com.etiya.catalogservice.repository.ProductRepository;
import com.etiya.catalogservice.service.abstracts.ProductOfferService;
import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.transport.kafka.producer.ProductCreatedProducer;
import com.etiya.common.events.product.CreateProductEvent;
import com.etiya.common.events.product.ProductCreatedEvent;
import com.etiya.catalogservice.service.dtos.responses.product.GetProductDetailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;

@Configuration
public class CreatedProductConsumer {

    private final ProductService productService;
    private final ProductCreatedProducer productCreatedProducer;
    private final ProductRepository productRepository;
    private final ProductOfferService productOfferService;
    private final Logger LOGGER = LoggerFactory.getLogger(CreatedProductConsumer.class);

    public CreatedProductConsumer(ProductService productService, ProductCreatedProducer productCreatedProducer, ProductRepository productRepository, ProductOfferService productOfferService) {
        this.productService = productService;
        this.productCreatedProducer = productCreatedProducer;
        this.productRepository = productRepository;
        this.productOfferService = productOfferService;
    }

    @Bean
    public Consumer<CreateProductEvent> productCreated() {

        return event -> {

            ProductOffer productOffer = productOfferService.getByEntityId(event.product_offer_id());

            Product product = new Product(
                    event.name(),
                    event.price(),
                    event.billingAccountId(),
                    productOffer
            );

            Product saved = productService.addProductByEntity(product);
            LOGGER.info(String.format("Consumed Product (created)=> %s", event));

            GetProductDetailResponse relatedProduct = productRepository.findCampaignOfferByProdId(saved.getId());

            ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(
                    event.id(),           // 🔥 Sales’e geri dönecek OrderItem
                    saved.getId(),
                    saved.getName(),
                    relatedProduct.getCampaignId(),
                    relatedProduct.getCampaignName()
            );

            productCreatedProducer.produceProductCreated(productCreatedEvent);

            LOGGER.info("Catalog: Product created & event published => " + productCreatedEvent);
        };
    };
    }
