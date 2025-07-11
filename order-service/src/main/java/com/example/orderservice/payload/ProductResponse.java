package com.example.orderservice.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductResponse implements Serializable {
    private Long id;
    private String name;
    private Double price;
    private Integer stock;
}
