package com.etiya.salesservice.service.concretes;

import com.etiya.common.responses.GetBasketResponse;
import com.etiya.salesservice.client.BasketServiceClient;
import com.etiya.salesservice.domain.Order;
import com.etiya.salesservice.domain.OrderItem;
import com.etiya.salesservice.domain.OrderItemCharValue;
import com.etiya.salesservice.repository.OrderRepository;
import com.etiya.salesservice.service.abstracts.OrderService;
import com.etiya.salesservice.service.dtos.requests.CreateOrderItemRequest;
import com.etiya.salesservice.service.dtos.requests.CreateOrderRequest;
import com.etiya.salesservice.service.dtos.responses.CreatedOrderResponse;
import com.etiya.salesservice.service.mappings.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BasketServiceClient basketServiceClient;

    public OrderServiceImpl(OrderRepository orderRepository, BasketServiceClient basketServiceClient) {
        this.orderRepository = orderRepository;
        this.basketServiceClient = basketServiceClient;
    }

    @Override
    public CreatedOrderResponse add(CreateOrderRequest createOrderRequest) {

        // 1️⃣ Sepeti getir
        GetBasketResponse basket = basketServiceClient.getByBillingAccount(createOrderRequest.getBillingAccountId());

        // 2️⃣ Basket → Order map'le
        Order order = OrderMapper.INSTANCE.orderFromGetBasketResponse(basket);

        // 3️⃣ FE’den gelen konfigürasyonları itemlara işle
        for (CreateOrderItemRequest itemReq : createOrderRequest.getItems()) {

            // Sepetteki item id → orderItem.id eşleşmesi
            OrderItem orderItem = order.getOrderItems()
                    .stream()
                    .filter(o -> o.getId().equals(itemReq.getBasketItemId()))
                    .findFirst()
                    .orElse(null);

            if (orderItem == null)
                continue;

            // CharValues doldur
            List<OrderItemCharValue> charValues =
                    OrderMapper.INSTANCE.orderItemCharValueListFromCreateRequestList(itemReq.getCharValues());

            orderItem.setOrderItemCharValues(charValues);
        }

        // 4️⃣ Kaydet
        Order saved = orderRepository.save(order);

        // 5️⃣ DTO döndür
        return OrderMapper.INSTANCE.createdOrderResponseFromOrder(saved);


        // TODO: Bu alanda basketservice tarafına istek atılıp sepetteki veriyi sipariş tarafına göndermek
        // => basketserviceclient.getByCustomerId(customerId)

        //Todo: Sipariş onaylandıktan sonra basket service tarafına sepetin boşaltılması için event fırlatılacak.
        // orderRepository.save(order);
        // var basketClearEvent = new BasketClearEvent(order.CustomerId);
        // producer.send(basketClearEvent)
    }
}