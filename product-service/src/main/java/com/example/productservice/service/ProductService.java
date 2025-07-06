package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.globalException.ProductNotFoundException;
import com.example.productservice.repo.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!!"));
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    @Transactional
    public void reduceStock(Long productId, int quantity) {
        Product product = getById(productId);
        if (product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }
        product.setStock(product.getStock() - quantity);
        repository.save(product);
    }


}
