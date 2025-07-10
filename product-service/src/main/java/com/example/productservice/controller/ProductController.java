package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.payload.ProductResponse;
import com.example.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        ProductResponse productResponse = service.getById(id);
        return productResponse;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }

    @PutMapping("/{id}/reduce")
    public void reduceStock(@PathVariable Long id, @RequestParam int quantity) {
        service.reduceStock(id, quantity);
    }
}
