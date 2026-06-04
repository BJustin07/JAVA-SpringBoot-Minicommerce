package com.medina.mini_commerce.Customer.dto;

import com.medina.mini_commerce.Order.OrdersDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerResponseWithOrdersDTO {
    private String customerName;
    private List<OrdersDTO> orders;
}
