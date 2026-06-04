package com.medina.mini_commerce.Order;

import com.medina.mini_commerce.Customer.CustomerService;
import com.medina.mini_commerce.Customer.dto.CustomerResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final CustomerService customerService;
    public OrdersService(OrdersRepository ordersRepository, CustomerService customerService){
        this.ordersRepository = ordersRepository;
        this.customerService = customerService;
    }

//    public List<OrdersDTO> getOrdersByCustomerId(Long customerId){
//
//
//        return new OrdersDTO();
//    }

}
