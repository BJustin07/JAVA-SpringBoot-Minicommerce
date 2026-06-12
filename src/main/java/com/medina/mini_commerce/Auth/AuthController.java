package com.medina.mini_commerce.Auth;

import com.medina.mini_commerce.Auth.dto.AuthLoginDTO;
import com.medina.mini_commerce.Auth.dto.AuthRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login (@Valid @RequestBody AuthLoginDTO authLoginDTO){
        return "hello you are at login";
    }
    @PostMapping("/signup")
    public String signup (@Valid @RequestBody AuthRegisterDTO authRegisterDTO){
        return "hello you are at signup";
    }
}
