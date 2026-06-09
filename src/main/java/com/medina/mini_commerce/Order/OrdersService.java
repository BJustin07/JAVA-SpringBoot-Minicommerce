package com.medina.mini_commerce.Order;

import com.medina.mini_commerce.Customer.Customer;
import com.medina.mini_commerce.Customer.CustomerRepository;
import com.medina.mini_commerce.Customer.CustomerService;
import com.medina.mini_commerce.Customer.Exceptions.CustomerNotFound;
import com.medina.mini_commerce.Order.dto.OrdersRequestDTO;
import com.medina.mini_commerce.Order.dto.OrdersDTO;
import com.medina.mini_commerce.Product.Product;
import com.medina.mini_commerce.Product.ProductRepository;
import com.medina.mini_commerce.Product.ProductService;
import com.medina.mini_commerce.Product.dto.ProductOrdersDTO;
import com.medina.mini_commerce.Product.dto.ProductRequestDTO;
import com.medina.mini_commerce.Product.dto.ProductResponseDTO;
import com.medina.mini_commerce.Product.exceptions.ProductNotFound;
import com.medina.mini_commerce.Product.exceptions.ProductOutOfStock;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    public OrdersService(OrdersRepository ordersRepository
    , CustomerRepository customerRepository
    , ProductRepository productRepository){
        this.ordersRepository = ordersRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public List<OrdersDTO> getOrdersByCustomerId(Long customerId){
        Customer customer = getCustomerById(customerId);
        List<Orders>orders = ordersRepository.findByCustomerId(customer.getId());
        Set<ProductOrdersDTO> ordersProducts = orders.stream()
                .flatMap(products -> products.getProducts().stream())
                .map(product -> new ProductOrdersDTO(
                        product.getProductCode(),
                        product.getProductDescription(),
                        product.getPrice()
                ))
                .collect(Collectors.toSet());


        return orders.stream()
                .map(order -> new OrdersDTO(
                        order.getOrderNumber(),
                        order.getOrderDate(),
                        ordersProducts,
                        order.getTotalOrderAmount()
                ))
                .toList();
    }

    @Transactional
    public String createOrderByCustomerId(OrdersRequestDTO ordersRequestDTO) {
        Double totalOrderAmount = 0.0;
        Customer customer = getCustomerById(ordersRequestDTO.getCustomerId());

        Set<Product> products = ordersRequestDTO.getProducts().stream()
                .map(productReqDTO -> productRepository.findByProductCode(productReqDTO.getProductCode())
                        .orElseThrow(() -> new ProductNotFound("Product does not exist")))
                .collect(Collectors.toSet());

        Map<String, Product> productMap = products.stream()
                .collect((Collectors.toMap(
                        product -> product.getProductCode(),
                                product -> product
                )));

        for (ProductRequestDTO productReq : ordersRequestDTO.getProducts()){
            Product product = productMap.get(productReq.getProductCode());
            totalOrderAmount += product.getPrice() * productReq.getQuantity();
            if( (product.getQuantity() - productReq.getQuantity()) < 0 ){
                throw new ProductOutOfStock("Could not process order, " +
                        "current stock cannot accommodate order request for product "
                        + product.getProductDescription()
                        + " with product code: " + product.getProductCode());
            }else{
                product.setQuantity(product.getQuantity() - productReq.getQuantity());
                productRepository.save(product);
            }
        }

        Orders orders = new Orders();
        orders.setOrderNumber(generateOrderNumber());
        orders.setCustomer(customer);
        orders.setOrderDate(LocalDate.now());
        orders.setTotalOrderAmount(totalOrderAmount);
        orders.setProducts(products);
        ordersRepository.save(orders);
        return "Successfully created order for customer: " + customer.getCustomerName();
    }


    public Customer getCustomerById(Long customerId){
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFound("Could not get orders for customer"));
    }

    public String generateOrderNumber(){
        return "ORD-" + UUID.randomUUID()
                .toString()
                .substring(0,7)
                .toUpperCase();
    }

}
