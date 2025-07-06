package com.example.orderservice.globalException;

import com.example.orderservice.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StockInsuficient.class)
    public ResponseEntity<ApiResponse> globalExceptionHandler(StockInsuficient stockInsuficient){

        ApiResponse response = ApiResponse.builder().message(stockInsuficient.getMessage())
                .success(true).status(HttpStatus.NOT_FOUND).build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
