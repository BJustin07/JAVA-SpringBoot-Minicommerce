package com.medina.mini_commerce.Order;

import com.medina.mini_commerce.Customer.Customer;
import com.medina.mini_commerce.Product.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer orderNumber;
    private LocalDate orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    private Set<Product> products;
    private Double totalOrderAmount;
}
