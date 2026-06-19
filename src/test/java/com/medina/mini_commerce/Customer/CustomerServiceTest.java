package com.medina.mini_commerce.Customer;

import com.medina.mini_commerce.Customer.dto.CustomerRequestDTO;
import com.medina.mini_commerce.Customer.dto.CustomerResponseDTO;
import com.medina.mini_commerce.Order.Orders;
import com.medina.mini_commerce.Product.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;
    @Test
    public void shouldCreateAndSaveNewCustomer(){
        CustomerRequestDTO customer = new CustomerRequestDTO(
                "email@some.com",
                "examplePassword",
                "exampleName",
                "09123495213",
                "someAddress"
        );
        Customer customerEntity = new Customer();
        customerEntity.setId(1);
        customerEntity.setCustomerAddress("exampleAddress");
        customerEntity.setCustomerName("exampleName");
        customerEntity.setCustomerEmail("email@some.com");
        customerEntity.setCustomerNumber("09123495213");
        customerEntity.setCustomerPassword("examplePassword");

        CustomerResponseDTO customerResponseDTO = customerService.createCustomer(customer);
        assertNotNull(customerResponseDTO);
        assertEquals("exampleName",
                customerResponseDTO.getCustomerName());

        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    public void shouldRetrieveAllCustomers(){
        List<Customer> customerListFromDB = exampleCustomers();
        when(customerRepository.findAll()).thenReturn(customerListFromDB);
        List<CustomerResponseDTO> customers = customerService.getAllCustomers();

        assertNotNull(customers);
        assertEquals(1, customerListFromDB.size());
        verify(customerRepository).findAll();

    }

    public List<Customer>exampleCustomers(){
        List<Customer> customerListFromDB = new ArrayList<>();
        List<Orders>orders = new ArrayList<>();
        Customer customer1 = new Customer();
        Orders orderForCustomer1 = new Orders();
        Set<Product> productCustomer1 = new HashSet<>();

        Product sampleProduct = new Product();
        sampleProduct.setId(1);
        sampleProduct.setQuantity(123);
        sampleProduct.setProductCode("prod-sample-001");
        sampleProduct.setPrice(555.555);
        sampleProduct.setProductDescription("sample1-product-desc");
        Product sampleProduct2 = new Product();
        sampleProduct2.setId(1);
        sampleProduct2.setQuantity(123);
        sampleProduct2.setProductCode("prod-sample-002");
        sampleProduct2.setPrice(777.777);
        sampleProduct2.setProductDescription("sample2-product-desc");
        productCustomer1.add(sampleProduct);
        productCustomer1.add(sampleProduct2);

        orderForCustomer1.setId(1);
        orderForCustomer1.setCustomer(customer1);
        orderForCustomer1.setOrderDate(LocalDate.ofEpochDay(12-05-2005));
        orderForCustomer1.setTotalOrderAmount(123.123);
        orderForCustomer1.setOrderNumber("ord-001");
        orderForCustomer1.setProducts(productCustomer1);

        orders.add(orderForCustomer1);

        customer1.setId(1);
        customer1.setCustomerName("customer1Name");
        customer1.setCustomerAddress("customer1Address");
        customer1.setCustomerNumber("09198989999");
        customer1.setCustomerEmail("customer1Email");
        customer1.setCustomerPassword("customer1Password");
        customer1.setOrders(orders);

        customerListFromDB.add(customer1);

        return customerListFromDB;
    }
}
