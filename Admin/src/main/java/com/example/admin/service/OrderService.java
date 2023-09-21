package com.example.admin.service;

import com.example.admin.domain.Order;
import com.example.admin.domain.OrderHistory;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    List<Order> getOrderConfirmedFalse(Boolean confirmed);

    Order getOrderById(Long id);

    List<OrderHistory> getAllOrders();

    List<OrderHistory> getOrderByTime(LocalDateTime startTime, LocalDateTime endTime);
}
