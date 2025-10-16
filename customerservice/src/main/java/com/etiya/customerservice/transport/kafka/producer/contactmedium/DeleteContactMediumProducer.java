package com.etiya.customerservice.transport.kafka.producer.contactmedium;

import com.etiya.common.events.address.DeleteAddressEvent;
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
public class DeleteContactMediumProducer {
    //private final KafkaTemplate<String, DeleteContactMediumEvent> kafkaTemplate;
    private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteContactMediumProducer.class);

    public DeleteContactMediumProducer(/*KafkaTemplate<String, DeleteContactMediumEvent> kafkaTemplate*/StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
        //this.kafkaTemplate = kafkaTemplate;
    }

    public void produceContactMediumDeleted(DeleteContactMediumEvent event){
        streamBridge.send("contactDeleted-out-0",event);
        LOGGER.info(String.format("Contact Medium deleted event => %s",event.id()));

       /* Message<DeleteContactMediumEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"delete-contactmedium").build();
        kafkaTemplate.send(message);*/
    }
}
