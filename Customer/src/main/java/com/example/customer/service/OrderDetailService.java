package com.example.customer.service;

import com.example.customer.domain.OrderDetail;
import com.example.customer.entity.OrderEntity;
import com.example.customer.entity.OrderHistoryEntity;

import java.util.List;

public interface OrderDetailService {
    void saveOrderDetail(OrderEntity orderEntity, List<OrderDetail> allCartItems);

    void saveOrderDetailHistory(OrderEntity orderEntity, OrderHistoryEntity orderHistoryEntity);
}
