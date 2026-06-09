package com.medina.mini_commerce.GlobalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class ErrorResponse {
    public static ResponseEntity<Map<String, Object>> errorResponseBuilder(HttpStatus status, String exceptionMessage){
        Map<String, Object> errorResponse = new LinkedHashMap<>();
        errorResponse.put("timestamp:", LocalDateTime.now());
        errorResponse.put("status:", status.value());
        errorResponse.put("error:", exceptionMessage);
        return new ResponseEntity<>(errorResponse, status);
    }
}
