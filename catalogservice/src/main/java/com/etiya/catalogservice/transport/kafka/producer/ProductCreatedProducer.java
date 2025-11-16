package com.etiya.catalogservice.transport.kafka.producer;

import com.etiya.common.events.product.ProductCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductCreatedProducer {
    private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCreatedProducer.class);

    public ProductCreatedProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void produceProductCreated(ProductCreatedEvent event){
        streamBridge.send("productDetailCreated-out-0",event);
        LOGGER.info(String.format("Product details created event => %s",event));

    }
}
