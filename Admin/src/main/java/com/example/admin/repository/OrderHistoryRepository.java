package com.example.admin.repository;

import com.example.admin.entity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity,Long> {


    List<OrderHistoryEntity> findOrderHistoryEntitiesByOrderDateTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

}
