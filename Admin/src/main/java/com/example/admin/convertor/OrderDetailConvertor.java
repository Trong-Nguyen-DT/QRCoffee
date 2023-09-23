package com.example.admin.convertor;

import com.example.admin.domain.OrderDetail;
import com.example.admin.entity.OrderDetailEntity;

public class OrderDetailConvertor {
    public static OrderDetail toModel (OrderDetailEntity orderDetailEntity){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(orderDetailEntity.getId());
        orderDetail.setOrder_id(orderDetailEntity.getOrderEntity().getId());
        orderDetail.setProduct_id(orderDetailEntity.getProductEntity().getId());
        orderDetail.setName(orderDetailEntity.getProductEntity().getTitle());
        orderDetail.setPrice(orderDetailEntity.getProductEntity().getPrice());
        orderDetail.setQuantity(orderDetailEntity.getQuantity());
        return orderDetail;
    }

    public static OrderDetail toModelMenu (OrderDetailEntity orderDetailEntity){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(orderDetailEntity.getId());
        orderDetail.setProduct_id(orderDetailEntity.getProductEntity().getId());
        orderDetail.setName(orderDetailEntity.getProductEntity().getTitle());
        orderDetail.setPrice(orderDetailEntity.getProductEntity().getPrice());
        orderDetail.setQuantity(orderDetailEntity.getQuantity());
        return orderDetail;
    }
}
