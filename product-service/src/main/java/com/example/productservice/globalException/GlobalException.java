package com.example.productservice.globalException;

import com.example.productservice.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse> globalExceptionHandler(ProductNotFoundException productNotFoundException){

       ApiResponse apiResponse =  ApiResponse.builder().message(productNotFoundException.getMessage()).success(true)
                .status(HttpStatus.NOT_FOUND).build();
       return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);

    }
}
