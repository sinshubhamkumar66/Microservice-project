package com.example.userservice.globalException;

import com.example.userservice.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> handlResourceNotFound(UserNotFoundException userNotFoundException){
       String message =  userNotFoundException.getMessage();
       ApiResponse apiResponse = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();

//        ApiResponse response = new ApiResponse.ApiResponseBuilder(userNotFoundException.getMessage())
//                .setSucess(true)
//                .setStatus(HttpStatus.NOT_FOUND).build();
                return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

}
