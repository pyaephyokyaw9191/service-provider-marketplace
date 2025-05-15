package com.eventorganizer.notification.service;

import com.eventorganizer.notification.model.Notification;
import com.eventorganizer.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public List<Notification> getUserNotifications(String userId) {
        return repository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public void markAsRead(String notificationId) {
        var notification = repository.findById(UUID.fromString(notificationId));
        notification.ifPresent(n -> {
            n.setRead(true);
            repository.save(n);
        });
    }
}
