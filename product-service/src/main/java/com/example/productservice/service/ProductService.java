package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.globalException.ProductNotFoundException;
import com.example.productservice.payload.ProductResponse;
import com.example.productservice.repo.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    @Lazy
    @Autowired
    private ProductService self;

    @Transactional
    @Cacheable(value = "product", key = "#id")
    public ProductResponse getById(Long id) {
        log.info("Fetching from DB");
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!!"));

        ProductResponse dto = new ProductResponse();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setStock(product.getStock());
        dto.setPrice(product.getPrice());

        return dto;
    }

    @Transactional
    @CachePut(value = "product", key = "#productId")
    public ProductResponse reduceStock(Long productId, int quantity) {
        ProductResponse product = self.getById(productId); // use proxy here

        if (product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        Product entity = repository.findById(productId).get();
        entity.setStock(entity.getStock() - quantity);
        Product savedProduct = repository.save(entity);

        ProductResponse dto = new ProductResponse();
        dto.setId(savedProduct.getId());
        dto.setName(savedProduct.getName());
        dto.setStock(savedProduct.getStock());
        dto.setPrice(savedProduct.getPrice());

        return dto;
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }
}

