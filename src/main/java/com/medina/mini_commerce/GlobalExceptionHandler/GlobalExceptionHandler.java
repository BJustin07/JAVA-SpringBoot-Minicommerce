package com.medina.mini_commerce.GlobalExceptionHandler;

import com.medina.mini_commerce.Customer.Exceptions.CustomerExists;
import com.medina.mini_commerce.Customer.Exceptions.CustomerNotFound;
import com.medina.mini_commerce.Product.exceptions.ProductAlreadyExists;
import com.medina.mini_commerce.Product.exceptions.ProductNotFound;
import com.medina.mini_commerce.Product.exceptions.ProductOutOfStock;
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
    public ResponseEntity<Map<String, Object>> handleCustomerNotFound(CustomerNotFound ex){
        return ErrorResponse.errorResponseBuilder(HttpStatus.NOT_FOUND, ex.getMessage());
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
        return ErrorResponse.errorResponseBuilder(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<Map<String,Object>>handleProductNotFound(ProductNotFound ex){
        return ErrorResponse.errorResponseBuilder(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ProductOutOfStock.class)
    public ResponseEntity<Map<String,Object>>handleProductOutOfStock(ProductOutOfStock ex){
        return ErrorResponse.errorResponseBuilder(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(CustomerExists.class)
    public ResponseEntity<Map<String,Object>>handleCustomerExists(CustomerExists ex){
        return ErrorResponse.errorResponseBuilder(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}

