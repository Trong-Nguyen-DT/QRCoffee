package com.example.admin.service;


import com.example.admin.domain.OrderDetail;
import com.example.admin.domain.Product;
import com.example.admin.entity.ProductEntity;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getItemsById(Long id);

    List<ProductEntity> getAllProduct();

    List<Product> getTop4Product();
}
