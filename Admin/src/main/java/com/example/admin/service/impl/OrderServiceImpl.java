package com.example.admin.service.impl;

import com.example.admin.convertor.OrderConvertor;
import com.example.admin.domain.Order;
import com.example.admin.repository.OrderRepository;
import com.example.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getOrderConfirmedFalse(Boolean confirmed) {
        return orderRepository.findOrderEntityByConfirmed(confirmed).stream().map(OrderConvertor::toModel).toList();
    }

    @Override
    public Order getOrderById(Long id) {
        return OrderConvertor.toModel(orderRepository.findById(id).orElseThrow());
    }
}
