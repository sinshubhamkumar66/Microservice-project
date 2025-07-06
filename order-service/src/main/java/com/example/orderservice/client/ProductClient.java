package com.example.orderservice.client;

import com.example.orderservice.payload.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    ProductResponse getProduct(@PathVariable Long id);

    @PutMapping("/api/products/{id}/reduce")
    void reduceStock(@PathVariable Long id, @RequestParam("quantity") int quantity);
}
