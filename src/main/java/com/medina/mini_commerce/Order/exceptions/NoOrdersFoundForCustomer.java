package com.medina.mini_commerce.Order.exceptions;

public class NoOrdersFoundForCustomer extends RuntimeException {
    public NoOrdersFoundForCustomer(String message) {
        super(message);
    }
}
