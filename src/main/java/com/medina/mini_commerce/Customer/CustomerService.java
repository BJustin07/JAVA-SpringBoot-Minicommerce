package com.medina.mini_commerce.Customer;

import com.medina.mini_commerce.Customer.Exceptions.CustomerNotFound;
import com.medina.mini_commerce.Customer.dto.CustomerRequestDTO;
import com.medina.mini_commerce.Customer.dto.CustomerResponseDTO;
import com.medina.mini_commerce.Customer.dto.CustomerResponseWithOrdersDTO;
import com.medina.mini_commerce.Order.OrdersService;
import com.medina.mini_commerce.Order.dto.OrdersDTO;
import com.medina.mini_commerce.Order.OrdersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private OrdersService ordersService;
    public CustomerService(CustomerRepository customerRepository, OrdersService ordersService) {
        this.customerRepository = customerRepository;
        this.ordersService = ordersService;
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer newCustomer = new Customer();
        newCustomer.setCustomerName(customerRequestDTO.getCustomerName());
        newCustomer.setCustomerNumber(String.valueOf(customerRequestDTO.getCustomerNumber()));
        newCustomer.setCustomerAddress(customerRequestDTO.getCustomerAddress());
        newCustomer.setCustomerEmail(customerRequestDTO.getCustomerEmail());
        newCustomer.setCustomerPassword(customerRequestDTO.getCustomerPassword());
        customerRepository.save(newCustomer);

        return new CustomerResponseDTO(newCustomer.getCustomerName());
    }

    public List<CustomerResponseDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> new CustomerResponseDTO(customer.getCustomerName()))
                .toList();
    }

    public CustomerResponseWithOrdersDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFound("Customer does not Exist"));
        List<OrdersDTO> customerOrders = ordersService.getOrdersByCustomerId(customer.getId());
        return new CustomerResponseWithOrdersDTO(
                customer.getCustomerName(),
                customerOrders
        );
    }
}
