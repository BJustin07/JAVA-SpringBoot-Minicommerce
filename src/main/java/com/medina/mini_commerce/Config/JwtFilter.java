package com.medina.mini_commerce.Config;

import com.medina.mini_commerce.Auth.AuthService;
import com.medina.mini_commerce.Auth.jwt.JwtService;
import com.medina.mini_commerce.Customer.CustomerRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomerRepository customerRepository;
    public JwtFilter(JwtService jwtService, CustomUserDetailsService customUserDetailsService
    , CustomerRepository customerRepository) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
        this. customerRepository = customerRepository;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(7).trim();
        String email = jwtService.extractClaims(token).getSubject();

        boolean isExistingCustomerEmail = email != null && customerRepository.findByCustomerEmail(email).isPresent();

        boolean isTokenExpired = jwtService.isTokenExpired(token);
        if (isExistingCustomerEmail && !isTokenExpired) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

            SecurityContextHolder.getContext().setAuthentication(authToken);
            System.out.println(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal());
        }
        filterChain.doFilter(request, response);
    }
}
