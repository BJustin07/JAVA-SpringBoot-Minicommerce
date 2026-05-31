package com.medina.mini_commerce.Customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        Customer newCustomer = new Customer();
        newCustomer.setCustomerName(customer.getCustomerName());
        newCustomer.setCustomerNumber(String.valueOf(customer.getCustomerNumber()));
        newCustomer.setCustomerAddress(customer.getCustomerAddress());
        newCustomer.setOrders(customer.getOrders());
        customerRepository.save(newCustomer);
        return newCustomer;
    }
}
