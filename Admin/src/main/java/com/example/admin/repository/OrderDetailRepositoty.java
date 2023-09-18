package com.example.admin.repository;

import com.example.admin.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepositoty extends JpaRepository<OrderDetailEntity, Long> {
}
