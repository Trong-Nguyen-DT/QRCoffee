package com.example.admin.convertor;

import com.example.admin.domain.OrderHistory;
import com.example.admin.entity.OrderHistoryEntity;

public class OrderHistoryConvertor {

    public static OrderHistory toModel(OrderHistoryEntity orderHistoryEntity){

        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setId(orderHistoryEntity.getId());
        orderHistory.setCustomerId(orderHistoryEntity.getCustomerId());
        orderHistory.setOrderDateTime(orderHistoryEntity.getOrderDateTime());
        orderHistory.setAmount(orderHistoryEntity.getAmount());
        orderHistory.setPoint(orderHistoryEntity.getPoint());
        orderHistory.setTotalPrice(orderHistoryEntity.getTotalPrice());

        return orderHistory;
    }
}
