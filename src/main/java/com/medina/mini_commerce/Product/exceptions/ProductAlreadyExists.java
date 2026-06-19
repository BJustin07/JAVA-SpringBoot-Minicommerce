package com.medina.mini_commerce.Product.exceptions;

public class ProductAlreadyExists extends RuntimeException {
    public ProductAlreadyExists(String message) {
        super(message);
    }
}
