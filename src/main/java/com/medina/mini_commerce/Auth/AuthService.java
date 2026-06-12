package com.medina.mini_commerce.Auth;

import com.medina.mini_commerce.Auth.dto.AuthLoginDTO;
import com.medina.mini_commerce.Auth.dto.AuthRegisterDTO;
import com.medina.mini_commerce.Auth.dto.AuthResponseDTO;
import com.medina.mini_commerce.Auth.jwt.JwtService;
import com.medina.mini_commerce.Customer.Customer;
import com.medina.mini_commerce.Customer.CustomerRepository;
import com.medina.mini_commerce.Customer.CustomerService;
import com.medina.mini_commerce.Customer.Exceptions.CustomerExists;
import com.medina.mini_commerce.Customer.Exceptions.CustomerNotFound;
import com.medina.mini_commerce.Customer.dto.CustomerRequestDTO;
import com.medina.mini_commerce.Customer.dto.CustomerResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    public AuthService(CustomerRepository customerRepository, CustomerService customerService,
                       JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponseDTO loginUser(AuthLoginDTO authLoginDTO) {
        Customer customer = customerRepository.findByCustomerEmail(authLoginDTO.getEmail())
                .orElseThrow(() -> new CustomerNotFound("Customer not found"));

        boolean validUser = passwordEncoder.matches(authLoginDTO.getPassword(), customer.getCustomerPassword());
        if (!validUser) {
            throw new CustomerNotFound("Customer incorrect credentials");
        }

        return new AuthResponseDTO(
                jwtService.generateJwtToken(authLoginDTO.getEmail()),
                "Successfully logged in."
        );

    }

    public AuthResponseDTO registerUser(AuthRegisterDTO authRegisterDTO){
        customerRepository.findByCustomerEmail(authRegisterDTO.getCustomerEmail())
                .orElseThrow(() -> new CustomerExists(" customerEmail already exists"));

        String hashedCustomerPassword = passwordEncoder.encode(authRegisterDTO.getCustomerPassword());
        CustomerResponseDTO customerResponseDTO = customerService.createCustomer(
                new CustomerRequestDTO(
                       authRegisterDTO.getCustomerEmail(),
                        hashedCustomerPassword,
                       authRegisterDTO.getCustomerName(),
                       authRegisterDTO.getCustomerNumber(),
                       authRegisterDTO.getCustomerAddress()
                )
        );

        String jwtToken = jwtService.generateJwtToken(authRegisterDTO.getCustomerEmail());

        return new AuthResponseDTO(
                jwtToken,
                customerResponseDTO.getCustomerName()
        );
    }
}
