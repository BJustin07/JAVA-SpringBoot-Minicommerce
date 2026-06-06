package com.medina.mini_commerce.Order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersRequestDTO {
    private Integer orderNumber;
    private Long customerId;

    //to add ung products
}
