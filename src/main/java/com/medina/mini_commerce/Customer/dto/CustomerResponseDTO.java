package com.medina.mini_commerce.Customer.dto;

import com.medina.mini_commerce.Customer.Customer;
import com.medina.mini_commerce.Order.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CustomerResponseDTO {
    private String customerName;
}
