package com.medina.mini_commerce.Product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrdersDTO {
    private String productCode;
    private String productDescription;
    private Double price;
}
