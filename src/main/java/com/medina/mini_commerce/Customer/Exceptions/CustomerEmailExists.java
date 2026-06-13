package com.medina.mini_commerce.Customer.Exceptions;

public class CustomerEmailExists extends RuntimeException {
    public CustomerEmailExists(String message) {
        super(message);
    }
}
