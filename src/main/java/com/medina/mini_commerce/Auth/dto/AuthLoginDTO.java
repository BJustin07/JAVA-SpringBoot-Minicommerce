package com.medina.mini_commerce.Auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthLoginDTO {
    @NotBlank(message = "email field cannot be blank")
    @Email
    String email;
    @NotBlank(message = "password field cannot be blank")
    String password;
}
