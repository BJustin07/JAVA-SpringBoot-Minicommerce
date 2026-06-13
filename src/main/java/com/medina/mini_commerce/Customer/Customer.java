package com.medina.mini_commerce.Customer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.medina.mini_commerce.Order.Orders;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customer")
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String customerEmail;
    private String customerPassword;
    private String customerName;
    private String customerNumber;
    private String customerAddress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonManagedReference
    private List<Orders> orders;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return this.customerPassword;
    }

    @Override
    public String getUsername() {
        return this.getCustomerEmail();
    }
}
