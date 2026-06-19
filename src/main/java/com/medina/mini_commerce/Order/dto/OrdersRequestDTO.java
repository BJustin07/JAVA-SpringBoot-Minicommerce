package com.medina.mini_commerce.Order.dto;

import com.medina.mini_commerce.Product.Product;
import com.medina.mini_commerce.Product.dto.ProductRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersRequestDTO {
    private Long customerId;
    private List<ProductRequestDTO> products;

    //to add ung products
}
