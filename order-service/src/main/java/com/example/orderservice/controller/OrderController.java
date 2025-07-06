package com.example.orderservice.controller;

import com.example.orderservice.Entity.Order;
import com.example.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> placeOrder(
            @RequestParam Long productId,
            @RequestParam Integer quantity
    ) {
        return ResponseEntity.ok(service.placeOrder(productId, quantity));
    }

    @GetMapping
    public List<Order> getAll() {
        return service.getAll();
    }
}

