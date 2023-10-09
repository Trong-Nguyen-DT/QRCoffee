package com.example.admin.service;

import com.example.admin.convertor.AmountData;
import com.example.admin.domain.*;
import com.example.admin.entity.OrderDetailHistoryEntity;
import com.example.admin.entity.OrderEntity;
import com.example.admin.entity.OrderHistoryEntity;
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

    Order confirmOrder(Long id, User user);

    OrderEntity saveOrder(Customer customer, Long totalPrice, int orderPoints, User user);

    Order setOrder(OrderEntity orderEntity);

    OrderEntity getOrderEntityById(Long orderId);

    OrderEntity setStatus(OrderEntity orderEntity);

    OrderHistoryEntity saveOrderHistory(OrderEntity orderEntity);

    List<OrderDetailHistoryEntity> getOrderDetailHistoryByOrderId(Long id);
}
