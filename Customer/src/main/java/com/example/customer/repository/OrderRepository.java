package com.example.customer.repository;

import com.example.customer.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findTopByOrderByOrderDateTimeDesc();
}
