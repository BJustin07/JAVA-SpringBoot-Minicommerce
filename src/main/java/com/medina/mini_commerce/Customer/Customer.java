package com.medina.mini_commerce.Customer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.medina.mini_commerce.Order.Orders;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String customerName;
    private String customerNumber;
    private String customerAddress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonManagedReference
    private List<Orders> orders;
}
