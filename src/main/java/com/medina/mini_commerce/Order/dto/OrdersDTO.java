package com.medina.mini_commerce.Order.dto;

import com.medina.mini_commerce.Product.Product;
import com.medina.mini_commerce.Product.dto.ProductOrdersDTO;
import com.medina.mini_commerce.Product.dto.ProductResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private String orderNumber;
    private LocalDate orderDate;
    private Set<ProductOrdersDTO> products;
    private Double totalOrderAmount;
}
