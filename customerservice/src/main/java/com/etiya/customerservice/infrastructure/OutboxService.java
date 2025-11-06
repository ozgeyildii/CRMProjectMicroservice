package com.etiya.customerservice.infrastructure;

public interface OutboxService {
    void save(Object event, String aggregateType, String aggregateId);
}
