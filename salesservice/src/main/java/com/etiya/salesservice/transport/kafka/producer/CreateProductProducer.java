package com.etiya.salesservice.transport.kafka.producer;

import com.etiya.common.events.product.CreateProductEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class CreateProductProducer {
    private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateProductProducer.class);

    public CreateProductProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void produceProductCreated(CreateProductEvent createProductEvent) {
        streamBridge.send("productCreated-out-0",createProductEvent);
        LOGGER.info(String.format("Product created event => %s",createProductEvent));

    }
}