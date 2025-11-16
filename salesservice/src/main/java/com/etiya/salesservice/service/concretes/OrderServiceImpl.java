package com.etiya.salesservice.service.concretes;

import com.etiya.common.events.product.CreateProductEvent;
import com.etiya.common.responses.GetAddressResponse;
import com.etiya.common.responses.GetBasketResponse;
import com.etiya.salesservice.client.BasketServiceClient;
import com.etiya.salesservice.client.CatalogServiceClient;
import com.etiya.salesservice.client.CustomerServiceClient;
import com.etiya.salesservice.domain.entities.Order;
import com.etiya.salesservice.domain.entities.OrderItem;
import com.etiya.salesservice.domain.entities.OrderItemCharValue;
import com.etiya.salesservice.domain.enums.OrderStatus;
import com.etiya.salesservice.repository.OrderRepository;
import com.etiya.salesservice.service.abstracts.OrderService;
import com.etiya.salesservice.service.dtos.requests.CreateOrderItemRequest;
import com.etiya.salesservice.service.dtos.requests.CreateOrderRequest;
import com.etiya.salesservice.service.dtos.responses.CreatedOrderItemResponse;
import com.etiya.salesservice.service.dtos.responses.CreatedOrderResponse;
import com.etiya.salesservice.service.mappings.OrderMapper;
import com.etiya.salesservice.transport.kafka.producer.ClearBasketProducer;
import com.etiya.salesservice.transport.kafka.producer.CreateProductProducer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

            List<OrderItemCharValue> charValues =
                    OrderMapper.INSTANCE.orderItemCharValueListFromCreateRequestList(itemReq.getCharValues());

            charValues.forEach(cv -> cv.setOrderItemId(orderItem.getId()));

            orderItem.setOrderItemCharValues(charValues);
        }

        GetAddressResponse addressResponse = customerServiceClient.getAddressById(createOrderRequest.getAddressId());

        order.setAddressId(addressResponse.getId());

        Order savedOrder = orderRepository.save(order);

        CreatedOrderResponse createdOrderResponse = OrderMapper.INSTANCE.createdOrderResponseFromOrder(savedOrder);

        createdOrderResponse.setAddress(addressResponse);

        clearBasketProducer.produceBasketCleared(basket.getBasketId());

        return createdOrderResponse;
    }

    @Override
    public void createProduct(String orderId) {
        Order existing= orderRepository.findById(orderId).orElseThrow(()-> new RuntimeException("Olmadı"));

        existing.getOrderItems().stream()
                .forEach(orderItem -> {
                    CreateProductEvent event = new CreateProductEvent(
                            orderItem.getId(),
                            orderItem.getProductOfferName(),
                            orderItem.getPrice().doubleValue(),
                            existing.getBillingAccountId(),
                            orderItem.getProductOfferId()
                    );
                    createProductProducer.produceProductCreated(event);
                });

        existing.setStatus(OrderStatus.COMPLETED);
        orderRepository.save(existing);
    }

    @Override
    public void updateProductInfo(String orderItemId, int productId, String productName, Integer campaignId, String campaignName) {
        Order order = orderRepository.findByOrderItems_Id(orderItemId)
                .orElseThrow(() -> new RuntimeException("Order not found for orderItemId: " + orderItemId));

        OrderItem item = order.getOrderItems().stream()
                .filter(oi -> oi.getId().equals(orderItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("OrderItem not found: " + orderItemId));

        item.setProductId(productId);
        item.setProductName(productName);
        item.setCampaignId(campaignId);
        item.setCampaignName(campaignName);

        orderRepository.save(order);
    }

    @Override
    public List<CreatedOrderResponse> getOrdersByBillingAccountId(int billingAccountId) {
        List<Order> orders = orderRepository.findAllByBillingAccountId(billingAccountId);

        if (orders == null || orders.isEmpty()) {
            return List.of();
        }

        return orders.stream()
                .map(order -> {
                    CreatedOrderResponse response =
                            OrderMapper.INSTANCE.createdOrderResponseFromOrder(order);

                    GetAddressResponse address =
                            customerServiceClient.getAddressById(order.getAddressId());
                    response.setAddress(address);

                    return response;
                })
                .toList();
    }
    }