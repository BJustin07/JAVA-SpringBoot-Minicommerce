package com.medina.mini_commerce.Customer.Exceptions;

public class CustomerNotFound extends RuntimeException {
    public CustomerNotFound(String message) {
        super(message);
    }
}
