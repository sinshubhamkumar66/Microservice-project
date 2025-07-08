package com.example.orderservice.service;

import com.example.orderservice.Entity.Order;
import com.example.orderservice.client.ProductClient;
import com.example.orderservice.globalException.StockInsuficient;
import com.example.orderservice.payload.NotificationRequest;
import com.example.orderservice.payload.ProductResponse;
import com.example.orderservice.producer.OrderProducer;
import com.example.orderservice.repo.OrderRepository;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
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
    @Retry(name = "productRetry", fallbackMethod = "productRetryFallBack")
    @RateLimiter(name = "productRateLimiter", fallbackMethod = "productRateLimiterFallBack")
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
    public String productRetryFallBack(Long productId, Integer quantity, Exception e) {
        return "❌ Product Service उपलब्ध नहीं है, कृपया बाद में प्रयास करें।";
    }

    // Rate Limiter के लिए fallback method
    public String productRateLimiterFallBack(Long productId, Integer quantity, RequestNotPermitted e) {
        return "⚠️ बहुत अधिक अनुरोध। कृपया कुछ समय बाद प्रयास करें।";
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}

