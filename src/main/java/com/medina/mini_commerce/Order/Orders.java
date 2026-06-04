package com.medina.mini_commerce.Order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.medina.mini_commerce.Customer.Customer;
import com.medina.mini_commerce.Product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer orderNumber;
    private LocalDate orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

//    @ManyToMany
//    @JoinTable(name = "orders_products",
//    joinColumns = @JoinColumn(name = "orders_id"),
//    inverseJoinColumns = @JoinColumn(name = "product_id"))
//    private Set<Product> products;

    private Double totalOrderAmount;
}
