package com.example.customer.service;

import com.example.customer.domain.OrderDetail;
import com.example.customer.entity.OrderEntity;

import java.util.List;

public interface OrderDetailService {
    void addOrderDetail(OrderEntity orderEntity, List<OrderDetail> allCartItems);
}
