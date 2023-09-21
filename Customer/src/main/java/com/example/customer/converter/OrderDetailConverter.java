package com.example.customer.converter;

import com.example.customer.domain.OrderDetail;
import com.example.customer.entity.OrderDetailEntity;

public class OrderDetailConverter {

    public static OrderDetail toModel (OrderDetailEntity orderDetailEntity){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct_id(orderDetailEntity.getProductEntity().getId());
        orderDetail.setName(orderDetailEntity.getProductEntity().getTitle());
        orderDetail.setPrice(orderDetailEntity.getProductEntity().getPrice());
        orderDetail.setQuantity(orderDetailEntity.getQuantity());
        return orderDetail;
    }
}
