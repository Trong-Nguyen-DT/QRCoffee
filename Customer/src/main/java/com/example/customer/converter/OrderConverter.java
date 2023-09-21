package com.example.customer.converter;

import com.example.customer.domain.Order;
import com.example.customer.entity.OrderEntity;

import java.util.stream.Collectors;

public class OrderConverter {

    public static Order toModel(OrderEntity orderEntity) {
        Order order = new Order();
        order.setId(orderEntity.getId());
        order.setCustomer_id(orderEntity.getCustomerEntity().getId());
        order.setUser_id(orderEntity.getId());
        order.setOrderDetails((orderEntity.getOrderDetails().stream().map(OrderDetailConverter::toModel).collect(Collectors.toList())));
        return order;
    }

}
