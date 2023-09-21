package com.example.admin.service.impl;

import com.example.admin.convertor.OrderConvertor;
import com.example.admin.convertor.OrderHistoryConvertor;
import com.example.admin.domain.Order;
import com.example.admin.domain.OrderHistory;
import com.example.admin.repository.OrderHistoryRepository;
import com.example.admin.repository.OrderRepository;
import com.example.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;


    @Override
    public List<Order> getOrderConfirmedFalse(Boolean confirmed) {
        return orderRepository.findOrderEntityByConfirmed(confirmed).stream().map(OrderConvertor::toModel).toList();
    }

    @Override
    public Order getOrderById(Long id) {
        return OrderConvertor.toModel(orderRepository.findById(id).orElseThrow());
    }

    @Override
    public List<OrderHistory> getAllOrders() {
        return  orderHistoryRepository.findAll()
                .stream().map(OrderHistoryConvertor::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderHistory> getOrderByTime(LocalDateTime startTime, LocalDateTime endTime) {
        return orderHistoryRepository.findOrderHistoryEntitiesByOrderDateTimeBetween(startTime, endTime).stream().map(OrderHistoryConvertor::toModel).toList();
    }

}
