package com.example.productservice.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductResponse implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
}
