package com.medina.mini_commerce.Product.dto;

import com.medina.mini_commerce.validation.OnCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    @NotBlank(message = "Product Code is Required", groups = {OnCreate.class})
    private String productCode;
    @NotBlank(message = "Product Description is Required" , groups = {OnCreate.class})
    private String productDescription;
    @NotNull(message = "Quantity is required", groups = {OnCreate.class})
    @Positive
    private Integer quantity;
    @NotNull(message = "Price is Required", groups = {OnCreate.class})
    @Positive
    private Double price;
}
