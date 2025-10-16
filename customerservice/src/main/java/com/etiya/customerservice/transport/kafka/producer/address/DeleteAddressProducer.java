package com.etiya.customerservice.transport.kafka.producer.address;

import com.etiya.common.events.address.DeleteAddressEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class DeleteAddressProducer {
    private final StreamBridge streamBridge;
   // private final KafkaTemplate<String, DeleteAddressEvent> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteAddressEvent.class);

    public DeleteAddressProducer(/*KafkaTemplate<String, DeleteAddressEvent> kafkaTemplate,*/ StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
       // this.kafkaTemplate = kafkaTemplate;
    }

    public void produceAddressDeleted(DeleteAddressEvent event){
        streamBridge.send("addressDeleted-out-0",event);
        LOGGER.info(String.format("Address delete event => %s",event.id()));
/*
        Message<DeleteAddressEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"delete-address").build();
        kafkaTemplate.send(message);*/
    }
}
