package com.medina.mini_commerce.Customer;

import com.medina.mini_commerce.Customer.dto.CustomerRequestDTO;
import com.medina.mini_commerce.Customer.dto.CustomerResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

        when(customerRepository.save(any(Customer.class)))
                .thenReturn(customerEntity);

        CustomerResponseDTO customerResponseDTO = customerService.createCustomer(customer);
        assertNotNull(customerResponseDTO);
        assertEquals("exampleName",
                customerResponseDTO.getCustomerName());

        verify(customerRepository).save(any(Customer.class));
    }
}
