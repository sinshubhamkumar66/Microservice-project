package com.example.orderservice.producer;

import com.example.orderservice.payload.NotificationRequest;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

@Component
public class OrderProducer {

    private  final KafkaTemplate<String, NotificationRequest> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, NotificationRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public  void sendNotification(NotificationRequest notification) {
        kafkaTemplate.send("order_notification", notification);
    }
}
