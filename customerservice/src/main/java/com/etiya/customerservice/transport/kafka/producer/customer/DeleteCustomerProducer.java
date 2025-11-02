package com.etiya.customerservice.transport.kafka.producer.customer;



import com.etiya.common.events.customer.DeleteCustomerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class DeleteCustomerProducer {
    //private final KafkaTemplate<String, DeleteContactMediumEvent> kafkaTemplate;
    private final StreamBridge streamBridge;
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteCustomerProducer.class);

    public DeleteCustomerProducer(/*KafkaTemplate<String, DeleteCustomerEvent> kafkaTemplate*/StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
        //this.kafkaTemplate = kafkaTemplate;
    }

    public void produceCustomerDeleted(DeleteCustomerEvent event){
        streamBridge.send("customerDeleted-out-0",event);
        LOGGER.info(String.format("Customer deleted event"));

       /* Message<DeleteCustomerEvent> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"delete-customer").build();
        kafkaTemplate.send(message);*/
    }
}
