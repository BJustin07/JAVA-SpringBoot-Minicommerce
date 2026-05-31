package com.medina.mini_commerce.Product;

import com.medina.mini_commerce.Order.Orders;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productCode;
    private String productDescription;
    private Integer quantity;
    private Double price;
    @ManyToMany
    private Set<Orders> order;
}
