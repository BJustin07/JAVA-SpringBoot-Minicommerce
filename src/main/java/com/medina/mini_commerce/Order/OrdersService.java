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
import com.medina.mini_commerce.Product.exceptions.ProductNotFound;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;
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

        //need to extract the ProductsDTO from ordersRequestDTO, to match
        //with the products entity retrieved from DB to get totalOrderAmount
        //and to be able to update the product entity quantity as well.
        Set<Product> products = ordersRequestDTO.getProducts().stream()
                .map(productReqDTO -> productRepository.findByProductCode(productReqDTO.getProductCode())
                        .orElseThrow(() -> new ProductNotFound("Product does not exist")))
                .collect(Collectors.toSet());

        Double totalOrderAmount = products.stream()
                .mapToDouble(product -> product.getPrice())
                .reduce(0.0,(sumPrice, currentPrice) -> sumPrice * );



        Orders orders = new Orders();
        orders.setOrderNumber(generateOrderNumber());
        orders.setCustomer(customer);
        orders.setOrderDate(LocalDate.now());
        orders.setTotalOrderAmount(totalOrderAmount);
        orders.setProducts(products);
        //To be filled out yung setProduct ng Product na entity
        // to edit yung setTotalOrderAmount kasi wala pa yung get products
        //dapat yung total ng products ung iseset na totalOrderAmount
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
