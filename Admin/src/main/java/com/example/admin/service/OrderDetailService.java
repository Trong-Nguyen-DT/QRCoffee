package com.example.admin.service;


import com.example.admin.domain.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getItemsById(Long id);
}
