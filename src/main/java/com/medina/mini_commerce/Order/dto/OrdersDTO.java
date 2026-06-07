package com.medina.mini_commerce.Order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private String orderNumber;
    private LocalDate orderDate;
//    private Set<ProductDTO> products;
    private Double totalOrderAmount;
}
