package com.etiya.salesservice.repository;

import com.etiya.salesservice.domain.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order,String> {
}