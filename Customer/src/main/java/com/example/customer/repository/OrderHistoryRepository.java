package com.example.customer.repository;

import com.example.customer.entity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity, Long> {

    List<OrderHistoryEntity> findAllByCustomerId(Long id);
    List<OrderHistoryEntity> findOrderDetailHistoryEntitiesById(Long id);


}
