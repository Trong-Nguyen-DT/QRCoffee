package com.example.customer.service;

import com.example.customer.domain.Customer;
import com.example.customer.entity.CustomerEntity;
import com.example.customer.entity.OrderEntity;

public interface CustomerService {

    void createMember(Customer customer);

    Customer getCustomerByPhone(String phoneNumber);

    void setPoint(OrderEntity orderEntity);
}
