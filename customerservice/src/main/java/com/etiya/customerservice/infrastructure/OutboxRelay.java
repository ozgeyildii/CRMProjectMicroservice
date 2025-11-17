package com.etiya.customerservice.infrastructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class OutboxRelay {

    private final OutboxEventRepository repo;
    private final StreamBridge bridge;
    Logger logger = LoggerFactory.getLogger(OutboxRelay.class);

    public OutboxRelay(OutboxEventRepository repo, StreamBridge bridge) {
        this.repo = repo;
        this.bridge = bridge;
    }

    @Scheduled(fixedDelay = 2000)
    @Transactional
    public void relay() {

        var events = repo.findAndLockNewEvents();
        if (events.isEmpty()) return;

        events.forEach(e -> {
            boolean published = bridge.send("createdEvents-out-0", e.getPayload());

            if (!published) {
                e.setStatus(OutboxEvent.Status.FAILED);
                logger.error("Outbox publish failed for aggregateType={} aggregateId={}",
                        e.getAggregateType(), e.getAggregateId());
                return;
            }

            e.setStatus(OutboxEvent.Status.PUBLISHED);
        });
    }

    @Scheduled(fixedDelay = 30000)
    @Transactional
    public void retryFailedEvents() {

        var failedEvents = repo.findAllByStatus(OutboxEvent.Status.FAILED);
        if (failedEvents.isEmpty()) {
            return;
        }

        failedEvents.forEach(event -> {
            boolean published = bridge.send("createdEvents-out-0", event.getPayload());

            if (published) {
                event.setStatus(OutboxEvent.Status.PUBLISHED);
            } else {
                logger.error("Outbox retry failed. aggregateType={} aggregateId={}",
                        event.getAggregateType(), event.getAggregateId());
            }
        });
    }

    @Scheduled(cron = "0 0 3 * * *")
    @Transactional
    public void cleanupOldPublishedEvents() {
        int deletedCount = repo.deleteOlderPublished(
                OutboxEvent.Status.PUBLISHED,
                LocalDateTime.now().minusDays(3)
        );
    }
}
