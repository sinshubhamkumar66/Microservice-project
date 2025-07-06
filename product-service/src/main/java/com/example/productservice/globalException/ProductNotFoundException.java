package com.example.productservice.globalException;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(){
        super("Product not Found !!");
    }

    public ProductNotFoundException(String message){
        super(message);
    }
}
