package com.example.notificationservice.consumer;
import com.example.notificationservice.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private final NotificationService service;

    public NotificationConsumer(NotificationService service) {
        this.service = service;
    }

    @KafkaListener(topics = "order_notification", groupId = "notification-group")
    public void listen(com.example.notificationservice.dto.NotificationRequest request) {
        service.send(request);
    }
}

