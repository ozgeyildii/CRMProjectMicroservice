package com.etiya.salesservice.transport.kafka.consumer;

import com.etiya.common.events.product.ProductCreatedEvent;
import com.etiya.salesservice.domain.entities.OrderItem;
import com.etiya.salesservice.service.abstracts.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class ProductCreatedConsumer {

    private final OrderService orderService;
    private final Logger LOGGER = LoggerFactory.getLogger(ProductCreatedConsumer.class);

    public ProductCreatedConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @Bean
    public Consumer<ProductCreatedEvent> productDetailCreated() {
        return event -> {
            orderService.updateProductInfo(
                    event.orderItemId(),
                    event.productId(),
                    event.productName(),
                    event.campaignId(),
                    event.campaignName()
            );

            LOGGER.info("Sales: ProductDetailCreatedEvent consumed for orderItemId={}", event.orderItemId());
        };
    }
}
