package com.etiya.customerservice.transport.kafka.producer.customer;

import com.etiya.common.events.CreateCustomerEvent;
import com.etiya.customerservice.domain.entities.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerProducer {

    private final KafkaTemplate<String, CreateCustomerEvent> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateCustomerProducer.class);

    public CreateCustomerProducer(KafkaTemplate<String, CreateCustomerEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceCustomerCreated(CreateCustomerEvent event){
        LOGGER.info(String.format("Customer created event => %s",event.customerId()));

        Message<CreateCustomerEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"create-customer").build();
        kafkaTemplate.send(message);
    }
}
