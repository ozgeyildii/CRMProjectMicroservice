package com.etiya.customerservice.transport.kafka.producer.contactmedium;

import com.etiya.common.events.contactmedium.CreateContactMediumEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class CreateContactMediumProducer {
    private final KafkaTemplate<String, CreateContactMediumEvent> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateContactMediumProducer.class);

    public CreateContactMediumProducer(KafkaTemplate<String, CreateContactMediumEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceContactMediumCreated(CreateContactMediumEvent event){
        LOGGER.info(String.format("Contact Medium created event => %s",event.id()));

        Message<CreateContactMediumEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"create-contactmedium").build();
        kafkaTemplate.send(message);
    }
}