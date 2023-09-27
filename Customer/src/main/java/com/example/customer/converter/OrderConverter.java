package com.example.customer.converter;

import com.example.customer.domain.Order;
import com.example.customer.entity.OrderEntity;
import com.example.customer.entity.OrderHistoryEntity;

import java.util.stream.Collectors;

public class OrderConverter {

    public static Order toModel(OrderEntity orderEntity) {
        Order order = new Order();
        order.setOrderCode(orderEntity.getId());
        order.setCustomer_id(orderEntity.getCustomerEntity().getId());
        order.setUser_id(orderEntity.getId());
        order.setItems((orderEntity.getOrderDetails().stream().map(OrderDetailConverter::toModel).collect(Collectors.toList())));
        return order;
    }

}
