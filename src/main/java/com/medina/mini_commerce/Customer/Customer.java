package com.medina.mini_commerce.Customer;

import com.medina.mini_commerce.Order.Orders;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String customerName;
    private String customerNumber;
    private String customerAddress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Orders> orders;
}
