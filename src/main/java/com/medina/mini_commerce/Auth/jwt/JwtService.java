package com.medina.mini_commerce.Auth.jwt;

import com.medina.mini_commerce.Customer.CustomerRepository;
import com.medina.mini_commerce.Customer.CustomerService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret.key}")
    private final String jwtSecretKey;

    public JwtService(String jwtSecretKey) {
        this.jwtSecretKey = jwtSecretKey;
    }

    public String generateJwtToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 300 * 1000))
                .signWith(signingKey())
                .compact();
    }

    private Key signingKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }



    //tapos create a filter that will utilize this JwtService
    //then add that filter sa SecurityConfig, and voila JWT baby, JUNIT HERE I COOOOOOOME
}
