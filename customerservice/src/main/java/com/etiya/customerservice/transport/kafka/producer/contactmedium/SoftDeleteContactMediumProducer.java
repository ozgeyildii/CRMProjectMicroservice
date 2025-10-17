package com.etiya.customerservice.transport.kafka.producer.contactmedium;

import com.etiya.common.events.address.SoftDeleteAddressEvent;
import com.etiya.common.events.contactmedium.SoftDeleteContactMediumEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class SoftDeleteContactMediumProducer {
    private final StreamBridge streamBridge;
    // private final KafkaTemplate<String, DeleteAddressEvent> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(SoftDeleteContactMediumEvent.class);

    public SoftDeleteContactMediumProducer(/*KafkaTemplate<String, SoftDeleteContactMediumProducer> kafkaTemplate,*/ StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
        // this.kafkaTemplate = kafkaTemplate;
    }

    public void produceAddressSoftDeleted(SoftDeleteContactMediumEvent event) {
        streamBridge.send("contactSoftDeleted-out-0", event);
        LOGGER.info(String.format("Contact medium soft delete event => %s", event.id()));
/*
        Message<DeleteAddressEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"delete-address").build();
        kafkaTemplate.send(message);*/
    }
}
