package com.etiya.customerservice.transport.kafka.producer.address;

import com.etiya.common.events.address.DeleteAddressEvent;
import com.etiya.common.events.address.SoftDeleteAddressEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class SoftDeleteAddressProducer {

        private final StreamBridge streamBridge;
        // private final KafkaTemplate<String, DeleteAddressEvent> kafkaTemplate;
        private static final Logger LOGGER = LoggerFactory.getLogger(SoftDeleteAddressEvent.class);

        public SoftDeleteAddressProducer(/*KafkaTemplate<String, SofDeleteAddressProducer> kafkaTemplate,*/ StreamBridge streamBridge) {
            this.streamBridge = streamBridge;
            // this.kafkaTemplate = kafkaTemplate;
        }

        public void produceAddressSoftDeleted(SoftDeleteAddressEvent event) {
            streamBridge.send("addressSoftDeleted-out-0", event);
            LOGGER.info(String.format("Address soft delete event => %s", event.id()));
/*
        Message<DeleteAddressEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"delete-address").build();
        kafkaTemplate.send(message);*/
        }

}
