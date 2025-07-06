package com.example.orderservice.payload;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private Integer stock;
}
