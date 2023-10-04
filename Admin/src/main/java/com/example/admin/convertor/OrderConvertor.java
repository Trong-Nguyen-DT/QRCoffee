package com.example.admin.convertor;

import com.example.admin.domain.Order;
import com.example.admin.domain.OrderDetail;
import com.example.admin.entity.OrderEntity;

import java.util.List;

public class OrderConvertor {
    public static Order toModel(OrderEntity orderEntity){
        Order order =  new Order();
        order.setAmount(orderEntity.getAmount());
        order.setPoint(orderEntity.getPoint());
        order.setTotal(orderEntity.getTotalPrice());
        order.setOrderCode(orderEntity.getId());
        order.setDescription(String.valueOf(orderEntity.getTableEntity().getName()));
        if (orderEntity.getCustomerEntity() != null) {
            order.setCustomer_id(orderEntity.getCustomerEntity().getId());
            order.setBuyerName(orderEntity.getCustomerEntity().getName());
            order.setBuyerPhone(orderEntity.getCustomerEntity().getPhone());
        }
        order.setStatus(orderEntity.getStatus());
        order.setItems(orderEntity.getOrderDetails().stream().map(OrderDetailConvertor::toModel).toList());
        order.setTableId(orderEntity.getTableEntity().getId());
        return order;
    }
}
