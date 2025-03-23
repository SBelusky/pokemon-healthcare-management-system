package com.sbelusky.microservices.notification_service.service;

import com.sbelusky.microservices.appointment_service.event.AppointmentCreatedEvent;
import com.sbelusky.microservices.notification_service.model.Notification;
import com.sbelusky.microservices.notification_service.model.enums.NotificationStatus;
import com.sbelusky.microservices.pokemon_service.event.PokemonCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 * This service listens to Kafka topics for PokemonCreatedEvent and
 * AppointmentCreatedEvent and sends email notifications to the owner of the
 * Pokemon or appointment.
 */
@Service
public class NotificationListener {
    private static final Logger log = LoggerFactory.getLogger(NotificationListener.class);
    private final JavaMailSender javaMailSender;
    private final NotificationService notificationService;

    @Value("${notification.set-from}")
    private String setFrom;

    public NotificationListener(JavaMailSender javaMailSender, NotificationService notificationService) {
        this.javaMailSender = javaMailSender;
        this.notificationService = notificationService;
    }

    /**
     * Listens to the "pokemon-created" topic and sends email notification to the owner
     * of the Pokemon.
     * @param pokemonCreatedEvent the PokemonCreatedEvent sent from the Pokemon service
     */
    @KafkaListener(topics = "pokemon-created")
    public void handlePokemonCreatedEvent(PokemonCreatedEvent pokemonCreatedEvent) {
        String subject = String.format("New Pokemon is here: %s",
                pokemonCreatedEvent.getName());

        String body = String.format("""
                Hi,
                
                You have successfully registered a pokemon named %s.
                
                Best Regards,
                Pokemon Healthcare Center
                """, pokemonCreatedEvent.getName());

        log.info("Received message from pokemon-created topic: {}", pokemonCreatedEvent);

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(setFrom);
            messageHelper.setTo(pokemonCreatedEvent.getOwnerEmail().toString());
            messageHelper.setSubject(String.format(subject));
            messageHelper.setText(body);
        };

        Notification notification = new Notification(
                setFrom,
                pokemonCreatedEvent.getOwnerEmail().toString(),
                subject,
                body,
                null,
                2L
                );

        try {
            javaMailSender.send(messagePreparator);
            log.info("Pokemon Created - Notification email sent!!");

            notification.setStatus(NotificationStatus.SENT);
            notificationService.createNotification(notification);
        } catch (MailException e) {
            log.error("Pokemon Created - Exception occurred when sending mail", e);

            notification.setStatus(NotificationStatus.ERROR);
            notificationService.createNotification(notification);

            throw new RuntimeException("Exception occurred when sending mail to " + pokemonCreatedEvent.getOwnerEmail(), e);
        }
    }

    /**
     * Listens to the "appointment-created" topic and sends email notification to the owner
     * of the appointment.
     * @param appointmentCreatedEvent the AppointmentCreatedEvent sent from the appointment service
     */
    @KafkaListener(topics = "appointment-created")
    public void handleAppointmentCreatedEvent(AppointmentCreatedEvent appointmentCreatedEvent) {
        String subject = String.format("New Appointment is here for pokemon: %s",
                appointmentCreatedEvent.getPokemonName());

        String body = String.format("""
            Hi,

            You have successfully registered an appointment for a pokemon named %s.

            Best Regards,
            Pokemon Healthcare Center
            """, appointmentCreatedEvent.getPokemonName());

        log.info("Received message from appoitment-created topic: {}", appointmentCreatedEvent);

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(setFrom);
            messageHelper.setTo(appointmentCreatedEvent.getOwnerEmail().toString());
            messageHelper.setSubject(String.format(subject));
            messageHelper.setText(body);
        };

        Notification notification = new Notification(
                setFrom,
                appointmentCreatedEvent.getOwnerEmail().toString(),
                subject,
                body,
                null,
                appointmentCreatedEvent.getOwnerId()
        );

        try {
            javaMailSender.send(messagePreparator);
            log.info("Appointment Created - Notification email sent!!");

            notification.setStatus(NotificationStatus.SENT);
            notificationService.createNotification(notification);
        } catch (MailException e) {
            log.error("Appointment Created - Exception occurred when sending mail", e);

            notification.setStatus(NotificationStatus.ERROR);
            notificationService.createNotification(notification);

            throw new RuntimeException("Exception occurred when sending mail to " + appointmentCreatedEvent.getOwnerEmail(), e);
        }
    }
}
