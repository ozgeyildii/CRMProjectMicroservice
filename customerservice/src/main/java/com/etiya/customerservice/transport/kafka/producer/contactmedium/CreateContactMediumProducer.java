package com.etiya.customerservice.transport.kafka.producer.contactmedium;

import com.etiya.common.events.contactmedium.CreateContactMediumEvent;
import com.etiya.common.events.contactmedium.DeleteContactMediumEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class CreateContactMediumProducer {
   // private final KafkaTemplate<String, CreateContactMediumEvent> kafkaTemplate;
   private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateContactMediumProducer.class);

    public CreateContactMediumProducer(/*KafkaTemplate<String, CreateContactMediumEvent> kafkaTemplate*/StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
        //this.kafkaTemplate = kafkaTemplate;
    }
    public void produceContactMediumCreated(CreateContactMediumEvent event){
        //Message<CreateContactMediumEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.KEY, event.customerId().toString()).build();
        streamBridge.send("contactCreated-out-0",event);
        LOGGER.info(String.format("Contact Medium created event => %s",event.id()));

       /* Message<DeleteContactMediumEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"delete-contactmedium").build();
        kafkaTemplate.send(message);*/
    }
}