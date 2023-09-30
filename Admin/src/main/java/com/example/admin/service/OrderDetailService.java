package com.example.admin.service;


import com.example.admin.domain.OrderDetail;
import com.example.admin.domain.Product;
import com.example.admin.entity.OrderDetailHistoryEntity;
import com.example.admin.entity.OrderEntity;
import com.example.admin.entity.OrderHistoryEntity;
import com.example.admin.entity.ProductEntity;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getItemsById(Long id);

    List<ProductEntity> getAllProduct();

    List<Product> getTop4Product();

    void saveOrderDetail(OrderEntity orderEntity, List<OrderDetail> allCartItems);

    void saveOrderDetailHistory(OrderEntity orderEntity, OrderHistoryEntity orderHistoryEntity);

    List<OrderDetailHistoryEntity> getAllDetailByOrderId(Long orderID);
}
