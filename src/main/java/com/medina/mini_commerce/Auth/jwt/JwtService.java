package com.medina.mini_commerce.Auth.jwt;

import com.medina.mini_commerce.Customer.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final CustomerRepository customerRepository;

    public JwtService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    //implement logic for logging in to get JWT token
}
