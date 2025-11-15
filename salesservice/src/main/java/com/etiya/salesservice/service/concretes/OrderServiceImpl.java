package com.etiya.salesservice.service.concretes;

import com.etiya.common.events.product.CreateProductEvent;
import com.etiya.common.responses.GetAddressResponse;
import com.etiya.common.responses.GetBasketResponse;
import com.etiya.salesservice.client.BasketServiceClient;
import com.etiya.salesservice.client.CustomerServiceClient;
import com.etiya.salesservice.domain.entities.Order;
import com.etiya.salesservice.domain.entities.OrderItem;
import com.etiya.salesservice.domain.entities.OrderItemCharValue;
import com.etiya.salesservice.repository.OrderRepository;
import com.etiya.salesservice.service.abstracts.OrderService;
import com.etiya.salesservice.service.dtos.requests.CreateOrderItemRequest;
import com.etiya.salesservice.service.dtos.requests.CreateOrderRequest;
import com.etiya.salesservice.service.dtos.responses.CreatedOrderResponse;
import com.etiya.salesservice.service.mappings.OrderMapper;
import com.etiya.salesservice.transport.kafka.producer.ClearBasketProducer;
import com.etiya.salesservice.transport.kafka.producer.CreateProductProducer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BasketServiceClient basketServiceClient;
    private final CustomerServiceClient customerServiceClient;
    private final ClearBasketProducer clearBasketProducer;
    private final CreateProductProducer createProductProducer;

    public OrderServiceImpl(OrderRepository orderRepository, BasketServiceClient basketServiceClient, CustomerServiceClient customerServiceClient, ClearBasketProducer clearBasketProducer, CreateProductProducer createProductProducer) {
        this.orderRepository = orderRepository;
        this.basketServiceClient = basketServiceClient;
        this.customerServiceClient = customerServiceClient;
        this.clearBasketProducer = clearBasketProducer;
        this.createProductProducer = createProductProducer;
    }

    @Override
    public CreatedOrderResponse add(CreateOrderRequest createOrderRequest) {

        GetBasketResponse basket = basketServiceClient.getBasketByBillingAccount(createOrderRequest.getBillingAccountId());

        Order order = OrderMapper.INSTANCE.orderFromGetBasketResponse(basket);

        for (CreateOrderItemRequest itemReq : createOrderRequest.getItems()) {


            OrderItem orderItem = order.getOrderItems()
                    .stream()
                    .filter(o -> o.getBasketItemId().equals(itemReq.getBasketItemId()))
                    .findFirst()
                    .orElse(null);
            if (orderItem == null)
                continue;
            // FE'den gelen char values → domain'e map
            List<OrderItemCharValue> charValues =
                    OrderMapper.INSTANCE.orderItemCharValueListFromCreateRequestList(itemReq.getCharValues());

            charValues.forEach(cv -> cv.setOrderItemId(orderItem.getId()));

            orderItem.setOrderItemCharValues(charValues);
        }

        GetAddressResponse addressResponse = customerServiceClient.getAddressById(createOrderRequest.getAddressId());

        order.setAddressId(addressResponse.getId());

        Order savedOrder = orderRepository.save(order);

        clearBasketProducer.produceBasketCleared(basket.getBasketId());

        savedOrder.getOrderItems().stream()
                .forEach(orderItem -> {
                    CreateProductEvent event = new CreateProductEvent(
                            orderItem.getProductOfferName(),
                            orderItem.getPrice().doubleValue(),
                            savedOrder.getBillingAccountId(),
                            orderItem.getProductOfferId()
                    );
                    createProductProducer.produceProductCreated(event);
                });

        CreatedOrderResponse createdOrderResponse = OrderMapper.INSTANCE.createdOrderResponseFromOrder(savedOrder);

        createdOrderResponse.setAddress(addressResponse);

        return createdOrderResponse;


        // TODO: Bu alanda basketservice tarafına istek atılıp sepetteki veriyi sipariş tarafına göndermek
        // => basketserviceclient.getByCustomerId(customerId)

        //Todo: Sipariş onaylandıktan sonra basket service tarafına sepetin boşaltılması için event fırlatılacak.
        // orderRepository.save(order);
        // var basketClearEvent = new BasketClearEvent(order.CustomerId);
        // producer.send(basketClearEvent)
    }
}