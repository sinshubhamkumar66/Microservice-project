package com.example.userservice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {
    private String message;
    private boolean success;
    private HttpStatus status;

//    ApiResponse(String message, boolean sucess, HttpStatus status)
//    {
//        this.message =  message;
//        this.success = sucess;
//        this.status = status;
//    }
//
//    public ApiResponse(ApiResponseBuilder apiResponseBuilder) {
//    }
//
//    public static class ApiResponseBuilder{
//        private String message;
//        private boolean success;
//        private HttpStatus status;
//
//        public ApiResponseBuilder(String message){
//            this.message = message;
//        }
//        public ApiResponseBuilder setSucess(boolean success){
//            this.success = success;
//            return this;
//        }
//        public ApiResponseBuilder setStatus(HttpStatus status){
//            this.status= status;
//            return  this;
//        }
//        public ApiResponse build(){
//            return  new ApiResponse(this);
//        }
//    }


}
