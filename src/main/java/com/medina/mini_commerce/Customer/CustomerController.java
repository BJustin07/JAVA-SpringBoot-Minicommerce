package com.medina.mini_commerce.Customer;

import com.medina.mini_commerce.Customer.dto.CustomerRequestDTO;
import com.medina.mini_commerce.Customer.dto.CustomerResponseDTO;
import com.medina.mini_commerce.Customer.dto.CustomerResponseWithOrdersDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String customerHelloWorld(){
        return "Hello World!";
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponseDTO>>getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseWithOrdersDTO> getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
}
