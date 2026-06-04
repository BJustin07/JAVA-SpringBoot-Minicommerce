package com.medina.mini_commerce.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    public List<Orders>findByCustomerId(Long id);
}
