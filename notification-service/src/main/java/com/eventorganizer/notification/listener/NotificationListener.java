package com.eventorganizer.notification.listener;

import com.eventorganizer.notification.model.Notification;
import com.eventorganizer.notification.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationListener {

    private final NotificationRepository repository;

    public NotificationListener(NotificationRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "notification-events", groupId = "notification-group")
    public void listen(String message) {

        Notification notification = Notification.builder()
                .userId("USER_ID_PLACEHOLDER")
                .message(message)
                .read(false)
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(notification);
    }
}
