package com.example.customer.service.Impl;

import com.example.customer.converter.CustomerConverter;
import com.example.customer.domain.Customer;
import com.example.customer.entity.OrderEntity;
import com.example.customer.repository.OrderRepository;
import com.example.customer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public OrderEntity addOrder(Integer tb, Customer customer) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderDateTime(LocalDateTime.now());
        orderEntity.setCustomerEntity(CustomerConverter.toEntity(customer));
        orderEntity.setTb(tb);
        orderEntity.setConfirmed(false);
        return orderRepository.save(orderEntity);
    }
}
