package com.medina.mini_commerce.Product;

import com.medina.mini_commerce.Order.Orders;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productCode;
    private String productDescription;
    private Integer quantity;
    private Double price;
//    @ManyToMany(mappedBy = "products")
//    private Set<Orders> orders;
}
