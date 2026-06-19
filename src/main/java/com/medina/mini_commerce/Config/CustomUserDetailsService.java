package com.medina.mini_commerce.Config;

import com.medina.mini_commerce.Customer.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;
    public CustomUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String customerEmail) throws UsernameNotFoundException {
        return customerRepository.findByCustomerEmail(customerEmail)
                .orElseThrow(() -> new UsernameNotFoundException("email does not exist"));
    }
}
