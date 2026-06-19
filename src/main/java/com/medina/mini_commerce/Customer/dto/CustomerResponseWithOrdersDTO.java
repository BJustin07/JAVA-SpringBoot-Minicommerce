package com.medina.mini_commerce.Customer.dto;

import com.medina.mini_commerce.Order.dto.OrdersDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseWithOrdersDTO {
    private String customerName;
    private List<OrdersDTO> orders;
}
