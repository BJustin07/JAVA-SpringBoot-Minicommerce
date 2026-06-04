package com.medina.mini_commerce.GlobalExceptionHandler;

import com.medina.mini_commerce.Customer.Exceptions.CustomerNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<String> handleCustomerNotFound(CustomerNotFound ex){
        return ResponseEntity.ok("Customer not found: " + ex);
    }
}
