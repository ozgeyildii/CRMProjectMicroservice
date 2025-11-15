package com.etiya.salesservice.transport.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class ClearBasketProducer {
    private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClearBasketProducer.class);

    public ClearBasketProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void produceBasketCleared(String basketId) {
        streamBridge.send("basketCleared-out-0",basketId);
        LOGGER.info(String.format("Basket cleared event => %s",basketId));

    }
}