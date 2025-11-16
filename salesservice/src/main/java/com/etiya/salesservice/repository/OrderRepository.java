package com.etiya.salesservice.repository;

import com.etiya.salesservice.domain.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order,String> {

    Optional<Order> findById(String orderId);
    Optional<Order> findByOrderItems_Id(String orderItemId);
    List<Order> findAllByBillingAccountId(int billingAccountId);

}