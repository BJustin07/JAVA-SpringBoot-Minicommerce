package com.medina.mini_commerce.Auth;

import com.medina.mini_commerce.Auth.dto.AuthLoginDTO;
import com.medina.mini_commerce.Auth.dto.AuthRegisterDTO;
import com.medina.mini_commerce.Auth.dto.AuthResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login (@Valid @RequestBody AuthLoginDTO authLoginDTO){
        return ResponseEntity.ok(authService.loginUser(authLoginDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> signup (@Valid @RequestBody AuthRegisterDTO authRegisterDTO){
        return ResponseEntity.ok(authService.registerUser(authRegisterDTO));
    }
}
