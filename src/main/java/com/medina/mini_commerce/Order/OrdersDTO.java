package com.medina.mini_commerce.Order;

import com.medina.mini_commerce.Customer.Customer;
import com.medina.mini_commerce.Product.Product;
import com.medina.mini_commerce.Product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
public class OrdersDTO {
    private Integer orderNumber;
    private LocalDate orderDate;
//    private Set<ProductDTO> products;
    private Double totalOrderAmount;
}
