package com.example.admin.service;

import com.example.admin.convertor.AmountData;
import com.example.admin.domain.Order;
import com.example.admin.domain.OrderHistory;
import com.example.admin.entity.ProductEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    List<Order> getOrderConfirmedFalse(Boolean confirmed);

    Order getOrderById(Long id);

    List<OrderHistory> getAllOrders();

    List<OrderHistory> getOrderByTime(LocalDateTime startTime, LocalDateTime endTime);

    Long getAllTotalByTime(LocalDateTime of, LocalDateTime now);

    List<AmountData> getAmountByMonth();
}
