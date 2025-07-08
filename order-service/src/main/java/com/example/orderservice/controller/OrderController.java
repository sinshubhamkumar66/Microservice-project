package com.example.orderservice.controller;

import com.example.orderservice.Entity.Order;
import com.example.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @CircuitBreaker(name = "verifyProduct", fallbackMethod = "verifyProductFallBack")
    @PostMapping
    public ResponseEntity<String> placeOrder(
            @RequestParam Long productId,
            @RequestParam Integer quantity
    ) {
        return ResponseEntity.ok(service.placeOrder(productId, quantity));
    }

    public ResponseEntity<String> verifyProductFallBack(Long productId, Integer quantity, Exception ex) {
        log.warn("Fallback called: product service is down. Reason: {}", ex.getMessage());
        return ResponseEntity.ok("Product service is currently unavailable. Please try again later.");
    }


    @GetMapping
    public List<Order> getAll() {
        return service.getAll();
    }
}

