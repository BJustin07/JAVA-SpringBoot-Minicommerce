package com.medina.mini_commerce.Order;

import com.medina.mini_commerce.Order.dto.OrdersDTO;
import com.medina.mini_commerce.Order.dto.OrdersRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService){
        this.ordersService = ordersService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<OrdersDTO>>getOrdersByCustomerId(@PathVariable Long customerId){
        return ResponseEntity.ok(ordersService.getOrdersByCustomerId(customerId));
    }

    @PostMapping("/")
    public ResponseEntity<String>createOrderByCustomerId(@RequestBody OrdersRequestDTO ordersRequestDTO){
        return ResponseEntity.ok(ordersService.createOrderByCustomerId(ordersRequestDTO));
    }

    //getOrdersByCustomerId
    //createOrdersByCustomerId
}
