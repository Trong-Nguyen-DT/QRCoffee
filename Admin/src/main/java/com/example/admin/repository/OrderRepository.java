package com.example.admin.repository;

import com.example.admin.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findOrderEntityByConfirmed(Boolean confirmed);
}
