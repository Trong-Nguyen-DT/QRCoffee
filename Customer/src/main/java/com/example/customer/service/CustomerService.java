package com.example.customer.service;

import com.example.customer.domain.Customer;
import com.example.customer.entity.CustomerEntity;

public interface CustomerService {

    void createMember(Customer customer);

    Customer getCustomerByPhone(String phoneNumber);
}
