package com.medina.mini_commerce.GlobalExceptionHandler;

import com.medina.mini_commerce.Customer.Exceptions.CustomerNotFound;
import com.medina.mini_commerce.Product.exceptions.ProductAlreadyExists;
import com.medina.mini_commerce.Product.exceptions.ProductNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<String> handleCustomerNotFound(CustomerNotFound ex){
        return ResponseEntity.ok("Customer not found: " + ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>>handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, Object> methodArgumentInvalidResponse = new LinkedHashMap<>();
        methodArgumentInvalidResponse.put("timestamp:", LocalDateTime.now());
        methodArgumentInvalidResponse.put("status:", HttpStatus.BAD_REQUEST.value());
        methodArgumentInvalidResponse.put("error:", ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> Map.of("field", error.getField(),
                        "Message:", error.getDefaultMessage() == null ? "" : error.getDefaultMessage()))
                .toList());
        return ResponseEntity.badRequest().body(methodArgumentInvalidResponse);
    }

    @ExceptionHandler(ProductAlreadyExists.class)
    public ResponseEntity<Map<String,Object>>handleProductAlreadyExists(ProductAlreadyExists ex){
        Map<String, Object> productAlreadyExistsResponse = new LinkedHashMap<>();
        productAlreadyExistsResponse.put("timestamp:", LocalDateTime.now());
        productAlreadyExistsResponse.put("status:", HttpStatus.BAD_REQUEST.value());
        productAlreadyExistsResponse.put("error:", ex.getMessage());

        return ResponseEntity.badRequest().body(productAlreadyExistsResponse);
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<Map<String,Object>>handleProductNotFound(ProductNotFound ex){
        Map<String, Object> ProductNotFoundResponse = new LinkedHashMap<>();
        ProductNotFoundResponse.put("timestamp:", LocalDateTime.now());
        ProductNotFoundResponse.put("status:", HttpStatus.NOT_FOUND.value());
        ProductNotFoundResponse.put("error:", ex.getMessage());

        return ResponseEntity.badRequest().body(ProductNotFoundResponse);
    }
}
