package com.etiya.customerservice.transport.kafka.producer.billlingaccount;

import com.etiya.common.events.address.CreateAddressEvent;
import com.etiya.common.events.billingaccount.CreateBillingAccountEvent;
import com.etiya.customerservice.transport.kafka.producer.address.CreateAddressProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class CreateBillingAccountProducer {
    private final StreamBridge streamBridge;
    //private final KafkaTemplate<String, CreateAddressEvent> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAddressProducer.class);

    public CreateBillingAccountProducer(StreamBridge streamBridge /*,KafkaTemplate<String, CreateAddressEvent> kafkaTemplate*/) {
        this.streamBridge = streamBridge;
        //this.kafkaTemplate = kafkaTemplate;
    }

    public void produceBillingAccountCreated(CreateBillingAccountEvent event){
        //Message<CreateAddressEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.KEY, event.customerId().toString()).build();
        streamBridge.send("billingAccountCreated-out-0",event);
        LOGGER.info(String.format("Billing Account created event => %s",event));

        /*Message<CreateAddressEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"create-address").build();
        kafkaTemplate.send(message);*/
    }
}
