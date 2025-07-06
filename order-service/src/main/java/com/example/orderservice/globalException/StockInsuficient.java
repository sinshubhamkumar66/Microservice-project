package com.example.orderservice.globalException;

public class StockInsuficient extends RuntimeException{

    public StockInsuficient()
    {
        super("insufficient product!!");
    }
    public StockInsuficient(String message)
    {
        super(message);
    }
}
