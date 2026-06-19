package com.medina.mini_commerce.GlobalExceptionHandler;

import com.medina.mini_commerce.Customer.Exceptions.CustomerEmailExists;
import com.medina.mini_commerce.Customer.Exceptions.CustomerNotFound;
import com.medina.mini_commerce.Order.exceptions.NoOrdersFoundForCustomer;
import com.medina.mini_commerce.Product.exceptions.ProductAlreadyExists;
import com.medina.mini_commerce.Product.exceptions.ProductNotFound;
import com.medina.mini_commerce.Product.exceptions.ProductOutOfStock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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

    @ExceptionHandler(CustomerEmailExists.class)
    public ResponseEntity<Map<String,Object>>handleCustomerExists(CustomerEmailExists ex){
        return ErrorResponse.errorResponseBuilder(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(NoOrdersFoundForCustomer.class)
    public ResponseEntity<Map<String,Object>>handleNoOrdersFoundForCustomer(NoOrdersFoundForCustomer ex){
        return ErrorResponse.errorResponseBuilder(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String,Object>>handleAccessDenied(AccessDeniedException ex){
        return ErrorResponse.errorResponseBuilder(HttpStatus.FORBIDDEN, ex.getMessage());
    }
}

