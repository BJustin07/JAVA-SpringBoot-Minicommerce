package com.medina.mini_commerce.Product.exceptions;

public class ProductOutOfStock extends RuntimeException {
    public ProductOutOfStock(String message) {
        super(message);
    }
}
