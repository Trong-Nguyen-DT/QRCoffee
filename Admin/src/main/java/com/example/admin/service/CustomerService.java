package com.example.admin.service;

import com.example.admin.domain.Customer;
import com.example.admin.entity.CustomerEntity;
import com.example.admin.entity.OrderEntity;

import java.util.List;

public interface CustomerService {
    List<CustomerEntity> getAllCustomers();

    Customer getCustomerByPhone(String phoneNumber);

    CustomerEntity getCustomerEntityByCustomer(Customer customer);

    void setPoint(CustomerEntity customerEntity, OrderEntity orderEntity);
}
