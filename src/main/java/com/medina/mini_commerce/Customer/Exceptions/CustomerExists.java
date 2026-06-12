package com.medina.mini_commerce.Customer.Exceptions;

public class CustomerExists extends RuntimeException {
    public CustomerExists(String message) {
        super(message);
    }
}
