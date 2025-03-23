package com.sbelusky.microservices.notification_service.repository;

import com.sbelusky.microservices.notification_service.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface provides a contract for the data access object (DAO) that is
 * responsible for managing the notifications in the database. It extends the
 * standard Spring Data JPA repository interface, which provides a set of common
 * methods (e.g. save, delete, find) for performing CRUD (Create, Read, Update,
 * Delete) operations on a given entity.
 */
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
