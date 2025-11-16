package com.etiya.customerservice.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

    @Repository
    public interface OutboxEventRepository extends JpaRepository<OutboxEvent, Long> {

        @Transactional
        @Query(
                value = "SELECT * FROM outbox_events " +
                        "WHERE status = 'NEW' " +
                        "ORDER BY created_at ASC " +
                        "FOR UPDATE SKIP LOCKED",
                nativeQuery = true
        )
        List<OutboxEvent> findAndLockNewEvents();

        int deleteAllByStatusAndCreatedAtBefore(OutboxEvent.Status status, LocalDateTime cutoff);

    }