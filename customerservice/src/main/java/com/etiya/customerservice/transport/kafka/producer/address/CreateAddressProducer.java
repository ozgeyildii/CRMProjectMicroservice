package com.etiya.customerservice.transport.kafka.producer.address;

import com.etiya.common.events.CreateAddressEvent;
import com.etiya.common.events.CreateCustomerEvent;
import com.etiya.customerservice.transport.kafka.producer.customer.CreateCustomerProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class CreateAddressProducer {
    private final KafkaTemplate<String, CreateAddressEvent> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAddressProducer.class);

    public CreateAddressProducer(KafkaTemplate<String, CreateAddressEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceAddressCreated(CreateAddressEvent event){
        LOGGER.info(String.format("Address created event => %s",event.id()));

        Message<CreateAddressEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"create-address").build();
        kafkaTemplate.send(message);
    }
}
