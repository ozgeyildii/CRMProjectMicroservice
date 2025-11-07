package com.etiya.salesservice.service.concretes;

import com.etiya.salesservice.repository.OrderRepository;
import com.etiya.salesservice.service.abstracts.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void add(String customerId) {

        // TODO: Bu alanda basketservice tarafına istek atılıp sepetteki veriyi sipariş tarafına göndermek
        // => basketserviceclient.getByCustomerId(customerId)

        //Todo: Sipariş onaylandıktan sonra basket service tarafına sepetin boşaltılması için event fırlatılacak.
        // orderRepository.save(order);
        // var basketClearEvent = new BasketClearEvent(order.CustomerId);
        // producer.send(basketClearEvent)


    }
}