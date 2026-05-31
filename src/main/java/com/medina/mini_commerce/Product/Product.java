package com.medina.mini_commerce.Product;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Entity
@Data
@RequiredArgsConstructor
public class Product {
    private long id;
    private String productCode;
    private String productDescription;
    private Integer quantity;
    private Double price;
}
