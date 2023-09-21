package com.example.admin.repository;

import com.example.admin.entity.OrderDetailEntity;
import com.example.admin.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
    List<OrderDetailEntity> findOrderDetailEntitiesByOrderEntity(Optional<OrderEntity> orderEntity);
}
