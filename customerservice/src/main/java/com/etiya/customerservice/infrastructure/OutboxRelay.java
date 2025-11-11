package com.etiya.customerservice.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@EnableScheduling
public class OutboxRelay {

    private static final Logger log = LoggerFactory.getLogger(OutboxRelay.class);
    private final OutboxEventRepository repo;
    private final StreamBridge bridge;

    public OutboxRelay(OutboxEventRepository repo, StreamBridge bridge) {
        this.repo = repo;
        this.bridge = bridge;
    }

    @Transactional
    @Scheduled(fixedDelay = 2000)
    public void publishOutboxEvents() {
        List<OutboxEvent> events = repo.findAndLockNewEvents();
        if (events.isEmpty()) return;

        for (OutboxEvent event : events) {
            try {
                Message<String> message = MessageBuilder.withPayload(event.getPayload())
                        .setHeader("contentType", "application/json")
                        .setHeader(KafkaHeaders.KEY, event.getAggregateId())
                        .build();
                bridge.send("createdEvents-out-0", message);
                event.setStatus(OutboxEvent.Status.PUBLISHED);

            } catch (Exception e) {
                log.error("❌ Failed to publish {}: {}", event.getEventType(), e.getMessage());
            }
        }
    }


    @Scheduled(cron = "0 0 3 * * *")
    public void cleanupOldPublishedEvents() {
        int deletedCount = repo.deleteAllByStatusAndCreatedAtBefore(
                OutboxEvent.Status.PUBLISHED,
                LocalDateTime.now().minusDays(3)
        );
        log.info("🧹 Cleaned up {} old published events (older than 3 days)", deletedCount);
    }
}
