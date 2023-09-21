package com.example.admin.service.impl;


import com.example.admin.entity.CustomerEntity;
import com.example.admin.repository.CustomerRepository;
import com.example.admin.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll().stream().toList();
    }
}
