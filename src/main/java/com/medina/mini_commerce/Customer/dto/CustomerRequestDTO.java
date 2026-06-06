package com.medina.mini_commerce.Customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.bridge.IMessage;

@Getter
@Setter
public class CustomerRequestDTO {
    private long id;
    @NotBlank(message = "customer name is required")
    private String customerName;
    @NotBlank(message = "customer number is required")
    @Size(min = 11, max = 11, message ="complete 11 digit customer number")
    private String customerNumber;
    @NotBlank(message = "customerAddress is required")
    private String customerAddress;
}
