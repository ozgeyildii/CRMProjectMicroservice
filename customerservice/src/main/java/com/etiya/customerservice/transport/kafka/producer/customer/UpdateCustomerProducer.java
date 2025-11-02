package com.etiya.customerservice.transport.kafka.producer.customer;

import com.etiya.common.events.customer.CreateCustomerEvent;
import com.etiya.common.events.customer.UpdateCustomerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class UpdateCustomerProducer {

    //private final KafkaTemplate<String, CreateCustomerEvent> kafkaTemplate;
    private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateCustomerProducer.class);

    public UpdateCustomerProducer(/*KafkaTemplate<String, CreateCustomerEvent> kafkaTemplate,*/ StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
        /* this.kafkaTemplate = kafkaTemplate;*/
    }

    public void produceCustomerUpdated(UpdateCustomerEvent event){
        streamBridge.send("customerUpdated-out-0",event);
        LOGGER.info(String.format("Customer updated event => %s",event.customerId()));
       /* Message<CreateCustomerEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"create-customer").build();
        kafkaTemplate.send(message);*/
    }
}