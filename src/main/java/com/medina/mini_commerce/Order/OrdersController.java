package com.medina.mini_commerce.Order;

import com.medina.mini_commerce.Customer.dto.CustomerRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService){
        this.ordersService = ordersService;
    }

//    public ResponseEntity<OrdersDTO>getOrdersByCustomerId(CustomerRequestDTO customer){
//        return ResponseEntity.ok(ordersService.getOrdersByCustomerId(customer.getId()));
//    }

    //getOrdersByCustomerId
    //createOrdersByCustomerId
}
