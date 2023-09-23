package com.example.customer.service;


import com.example.customer.domain.Customer;
import com.example.customer.domain.Order;
import com.example.customer.entity.OrderEntity;
import com.example.customer.entity.OrderHistoryEntity;

public interface OrderService {

    OrderEntity saveOrder(Integer tb, Customer customer, Long totalPrice, Integer point);

    OrderEntity getOrderById(Long id);

    Order setOrder(OrderEntity orderEntity);

    OrderHistoryEntity saveOrderHistory(OrderEntity orderEntity);

    OrderEntity setStatus(OrderEntity orderEntity);
}
