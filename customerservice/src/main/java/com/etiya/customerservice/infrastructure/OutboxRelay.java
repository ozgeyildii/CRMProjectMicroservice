package com.etiya.customerservice.infrastructure;

import com.etiya.common.crosscuttingconcerns.exceptions.constants.ExceptionMessages;
import com.etiya.common.crosscuttingconcerns.exceptions.types.InternalServerException;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class OutboxRelay {

    private final OutboxEventRepository repo;
    private final StreamBridge bridge;

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
            try {
                bridge.send("createdEvents-out-0", e.getPayload());
                e.setStatus(OutboxEvent.Status.PUBLISHED);
            } catch (Exception ex) {
                throw new InternalServerException(ExceptionMessages.OUTBOX_SERIALIZATION_ERROR);
            }});
    }

    @Scheduled(cron = "0 0 3 * * *")
    public void cleanupOldPublishedEvents() {
        int deletedCount = repo.deleteAllByStatusAndCreatedAtBefore(
                OutboxEvent.Status.PUBLISHED,
                LocalDateTime.now().minusDays(3)
        );
    }
}
