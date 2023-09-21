package com.example.admin.service;

import com.example.admin.domain.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrderConfirmedFalse(Boolean confirmed);

    Order getOrderById(Long id);
}
