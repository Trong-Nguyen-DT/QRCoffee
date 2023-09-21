package com.example.admin.service.impl;

import com.example.admin.convertor.OrderDetailConvertor;
import com.example.admin.domain.OrderDetail;
import com.example.admin.repository.OrderDetailRepository;
import com.example.admin.repository.OrderRepository;
import com.example.admin.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderDetail> getItemsById(Long id) {
        return orderDetailRepository.findOrderDetailEntitiesByOrderEntity(orderRepository.findById(id)).stream().map(OrderDetailConvertor::toModel).toList();
    }
}
