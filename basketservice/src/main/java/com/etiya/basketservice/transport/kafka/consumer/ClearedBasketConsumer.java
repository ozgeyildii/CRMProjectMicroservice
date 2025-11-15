package com.etiya.basketservice.transport.kafka.consumer;

import com.etiya.basketservice.service.abstracts.BasketService;
import com.etiya.common.events.billingaccount.CreateBillingAccountEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class ClearedBasketConsumer {

    private final BasketService basketService;
    private final Logger LOGGER = LoggerFactory.getLogger(ClearedBasketConsumer.class);

    public ClearedBasketConsumer(BasketService basketService) {
        this.basketService = basketService;
    }

    @Bean
    public Consumer<String> basketCleared() {
        return event -> {

            basketService.clearBasket(event);
            LOGGER.info(String.format("Consumed clear basket event => %s", event));
        };
    }
}