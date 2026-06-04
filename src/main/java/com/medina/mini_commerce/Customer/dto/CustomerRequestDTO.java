package com.medina.mini_commerce.Customer.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDTO {
    private long id;
    private String customerName;
    private String customerNumber;
    private String customerAddress;
}
