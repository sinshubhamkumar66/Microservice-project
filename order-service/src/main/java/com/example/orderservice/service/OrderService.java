package com.example.orderservice.service;

import com.example.orderservice.Entity.Order;
import com.example.orderservice.client.ProductClient;
import com.example.orderservice.globalException.StockInsuficient;
import com.example.orderservice.payload.NotificationRequest;
import com.example.orderservice.payload.ProductResponse;
import com.example.orderservice.producer.OrderProducer;
import com.example.orderservice.repo.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderProducer orderProducer;

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public OrderService(OrderRepository orderRepository, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    @Transactional()
    public String placeOrder(Long productId, Integer quantity) {
        ProductResponse product = productClient.getProduct(productId);

        if (product.getStock() < quantity) {
            throw new StockInsuficient("stock not available: "+quantity);
        }

        productClient.reduceStock(productId, quantity); // update in product-service

        Order order = Order.builder()
                .productId(productId)
                .quantity(quantity)
                .status("PLACED")
                .build();
        orderRepository.save(order);


        // Send Kafka event (optional enhancement)
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setTo("singh@gmail.com");
        notificationRequest.setMessage("order placed with this id"+productId);
        notificationRequest.setType("Email");
        orderProducer.sendNotification(notificationRequest);
        return "Order placed successfully!";
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}

