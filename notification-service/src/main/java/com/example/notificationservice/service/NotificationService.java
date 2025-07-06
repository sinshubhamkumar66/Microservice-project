package com.example.notificationservice.service;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {

    public void send( com.example.notificationservice.dto.NotificationRequest request) {
        System.out.println("📨 Notification received:");
        System.out.println("To: " + request.getTo());
        System.out.println("Type: " + request.getType());
        System.out.println("Message: " + request.getMessage());

        // Here you can add Email, SMS, or Push logic
    }
}

