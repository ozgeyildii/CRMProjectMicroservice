package com.etiya.customerservice.transport.kafka.producer.contactmedium;

import com.etiya.common.events.contactmedium.CreateContactMediumEvent;
import com.etiya.common.events.contactmedium.UpdateContactMediumEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UpdateContactMediumProducer {
   // private final KafkaTemplate<String, UpdateContactMediumEvent> kafkaTemplate;
   private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateContactMediumProducer.class);

    public UpdateContactMediumProducer(/*KafkaTemplate<String, UpdateContactMediumEvent> kafkaTemplate*/StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
        //this.kafkaTemplate = kafkaTemplate;
    }

    public void produceContactMediumUpdated(UpdateContactMediumEvent event){
        streamBridge.send("contactUpdated-out-0",event);
        LOGGER.info(String.format("Contact Medium updated event => %s",event.id()));

      /*  Message<UpdateContactMediumEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"update-contactmedium").build();
        kafkaTemplate.send(message);*/
    }
}