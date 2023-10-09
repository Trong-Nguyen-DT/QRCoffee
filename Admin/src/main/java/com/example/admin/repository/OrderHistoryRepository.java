package com.example.admin.repository;

import com.example.admin.entity.OrderDetailHistoryEntity;
import com.example.admin.entity.OrderEntity;
import com.example.admin.entity.OrderHistoryEntity;
import com.example.admin.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity,Long> {


    List<OrderHistoryEntity> findOrderHistoryEntitiesByOrderDateTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
//
//    @Query("SELECT MONTH(o.dateTime) AS month, YEAR(o.dateTime) AS year, SUM(o.amount) AS totalAmount " +
//            "FROM OrderHistoryEntity o " +
//            "GROUP BY MONTH(o.dateTime), YEAR(o.dateTime) " +
//            "ORDER BY YEAR(o.dateTime), MONTH(o.dateTime)")
//    List<Object[]> getTotalAmountByMonth();

    @Query("SELECT o FROM OrderHistoryEntity o WHERE MONTH(o.orderDateTime) = :month AND YEAR(o.orderDateTime) = :year")
    List<OrderHistoryEntity> findOrdersByMonthAndYear(int month, int year);

    List<OrderHistoryEntity> findOrderHistoryEntitiesByUserIdAndOrderDateTimeBetween(Long id, LocalDateTime start, LocalDateTime end);
}
