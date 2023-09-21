package com.example.customer.service;


import com.example.customer.domain.Customer;
import com.example.customer.entity.OrderEntity;

public interface OrderService {

    OrderEntity addOrder(Integer tb, Customer customer);
}
