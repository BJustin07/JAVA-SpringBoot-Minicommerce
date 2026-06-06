package com.medina.mini_commerce.Order;

import com.medina.mini_commerce.Customer.Customer;
import com.medina.mini_commerce.Customer.CustomerRepository;
import com.medina.mini_commerce.Customer.CustomerService;
import com.medina.mini_commerce.Customer.Exceptions.CustomerNotFound;
import com.medina.mini_commerce.Order.dto.OrdersRequestDTO;
import com.medina.mini_commerce.Order.dto.OrdersDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;
    public OrdersService(OrdersRepository ordersRepository
    ,CustomerRepository customerRepository){
        this.ordersRepository = ordersRepository;
        this.customerRepository = customerRepository;
    }

    public List<OrdersDTO> getOrdersByCustomerId(Long customerId){
        Customer customer = getCustomerById(customerId);
        List<Orders>orders = ordersRepository.findByCustomerId(customer.getId());

        return orders.stream()
                .map(order -> new OrdersDTO(
                        order.getOrderNumber(),
                        order.getOrderDate(),
                        order.getTotalOrderAmount()
                ))
                .toList();
    }

    @Transactional
    public String createOrderByCustomerId(OrdersRequestDTO ordersRequestDTO){
        Customer customer = getCustomerById(ordersRequestDTO.getCustomerId());
        Orders orders = new Orders();
        orders.setOrderNumber(5555);
        //create utility for OrderNumber
        orders.setCustomer(customer);
        orders.setOrderDate(LocalDate.now());
        orders.setTotalOrderAmount(12345.12345);
        // to edit yung setTotalOrderAmount kasi wala pa yung get products
        //dapat yung total ng products ung iseset na totalOrderAmount
        ordersRepository.save(orders);
        return "Successfully created order for customer: " + customer.getCustomerName();
    }


    public Customer getCustomerById(Long customerId){
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFound("Could not get orders for customer"));
    }

}
