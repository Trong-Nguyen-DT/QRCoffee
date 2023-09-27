package com.example.customer.service;

import com.example.customer.domain.Customer;
import com.example.customer.domain.Order;
import com.example.customer.entity.CustomerEntity;
import com.example.customer.entity.OrderDetailHistoryEntity;
import com.example.customer.entity.OrderEntity;
import com.example.customer.entity.OrderHistoryEntity;

import java.util.List;

public interface CustomerService {

    void createMember(Customer customer);

    Customer getCustomerByPhone(String phoneNumber);

    void setPoint(OrderEntity orderEntity);

    List<OrderHistoryEntity> getAllOrderByCustomerId(Long id);

    List<OrderDetailHistoryEntity> getAllOrderDetailByOrderId(Long id);
}
