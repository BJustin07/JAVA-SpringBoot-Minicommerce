package com.medina.mini_commerce.Customer;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.medina.mini_commerce.Customer.Exceptions.CustomerNotFound;
import com.medina.mini_commerce.Customer.dto.CustomerRequestDTO;
import com.medina.mini_commerce.Customer.dto.CustomerResponseDTO;
import com.medina.mini_commerce.Customer.dto.CustomerResponseWithOrdersDTO;
import com.medina.mini_commerce.Order.Orders;
import com.medina.mini_commerce.Order.OrdersDTO;
import com.medina.mini_commerce.Order.OrdersRepository;
import com.medina.mini_commerce.Product.ProductDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private OrdersRepository ordersRepository;
    public CustomerService(CustomerRepository customerRepository, OrdersRepository ordersRepository) {
        this.customerRepository = customerRepository;
        this.ordersRepository = ordersRepository;
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer newCustomer = new Customer();
        newCustomer.setCustomerName(customerRequestDTO.getCustomerName());
        newCustomer.setCustomerNumber(String.valueOf(customerRequestDTO.getCustomerNumber()));
        newCustomer.setCustomerAddress(customerRequestDTO.getCustomerAddress());
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
        List<OrdersDTO> ordersDTO = new ArrayList<>();
        ordersDTO.add(new OrdersDTO(
                123,
                LocalDate.now(),
                50000000.0
        ));
        CustomerResponseWithOrdersDTO customerResponseWithOrdersDTO = new CustomerResponseWithOrdersDTO(
                customer.getCustomerName(),
                ordersDTO
        );


        return customerResponseWithOrdersDTO;
    }
}
