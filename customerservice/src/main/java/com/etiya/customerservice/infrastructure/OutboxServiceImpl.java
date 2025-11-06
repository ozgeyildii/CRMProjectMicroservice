package com.etiya.customerservice.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class OutboxServiceImpl implements OutboxService {
    private final OutboxEventRepository repo;
    private final ObjectMapper mapper;

    public OutboxServiceImpl(OutboxEventRepository repo, ObjectMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void save(Object event, String aggregateType, String aggregateId) {
        OutboxEvent e = new OutboxEvent();
        e.setAggregateType(aggregateType);
        e.setAggregateId(aggregateId);
        e.setEventType(event.getClass().getSimpleName());
        e.setPayload(serialize(event));
        repo.save(e);
    }
        private String serialize(Object event) {
            try {
                Map<String, Object> map = mapper.convertValue(event, Map.class);
                map.put("eventType", event.getClass().getSimpleName());
                return mapper.writeValueAsString(map);
            } catch (Exception e) {
                throw new IllegalStateException("❌ Failed to serialize event: " + event.getClass().getSimpleName(), e);
            }

    }}