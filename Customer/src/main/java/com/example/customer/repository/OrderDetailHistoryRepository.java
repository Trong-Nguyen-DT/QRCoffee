package com.example.customer.repository;

import com.example.customer.entity.OrderDetailHistoryEntity;
import com.example.customer.entity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailHistoryRepository extends JpaRepository<OrderDetailHistoryEntity, Long> {

    List<OrderDetailHistoryEntity> findAllByOrderHistoryEntity(OrderHistoryEntity orderHistoryEntity);
}
