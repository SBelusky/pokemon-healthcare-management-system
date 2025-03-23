package com.sbelusky.microservices.notification_service.service;

import com.sbelusky.microservices.notification_service.model.Notification;
import com.sbelusky.microservices.notification_service.repository.NotificationRepository;
import org.springframework.stereotype.Service;

/**
 * Service class for managing notifications.
 */
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    /**
     * Constructs a new {@link NotificationService} with the given {@link NotificationRepository}.
     *
     * @param notificationRepository the repository to use for persisting notifications
     */
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    /**
     * Creates a new notification in the database.
     *
     * @param notification the notification to create
     */
    public void createNotification(Notification notification) {
        notificationRepository.save(notification);
    }
}