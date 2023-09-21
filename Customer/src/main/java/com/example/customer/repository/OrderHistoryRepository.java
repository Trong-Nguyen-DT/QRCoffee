package com.example.customer.repository;

import com.example.customer.entity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity, Long> {
}
