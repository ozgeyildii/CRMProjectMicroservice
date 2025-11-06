package com.etiya.customerservice.transport.kafka.producer.customer;

import com.etiya.common.events.address.CreateAddressEvent;
import com.etiya.common.events.customer.CreateCustomerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
@Service
public class CreateCustomerProducer {

    //private final KafkaTemplate<String, CreateCustomerEvent> kafkaTemplate;
    private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateCustomerProducer.class);

    public CreateCustomerProducer(/*KafkaTemplate<String, CreateCustomerEvent> kafkaTemplate,*/ StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
        /* this.kafkaTemplate = kafkaTemplate;*/
    }

    public void produceCustomerCreated(CreateCustomerEvent event){
        //Message<CreateCustomerEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.KEY, event.customerId().toString()).build();
        streamBridge.send("customerCreated-out-0",event);
        LOGGER.info(String.format("Customer created event => %s",event.customerId()));
       /* Message<CreateCustomerEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"create-customer").build();
        kafkaTemplate.send(message);*/
    }
}