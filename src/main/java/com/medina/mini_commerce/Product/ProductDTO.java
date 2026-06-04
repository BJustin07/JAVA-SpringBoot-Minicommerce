package com.medina.mini_commerce.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
    private long id;
    private String productDescription;
    private Integer quantity;
    private Double price;
}
