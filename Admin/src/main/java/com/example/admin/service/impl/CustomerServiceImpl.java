package com.example.admin.service.impl;


import com.example.admin.convertor.CustomerConvertor;
import com.example.admin.domain.Customer;
import com.example.admin.entity.CustomerEntity;
import com.example.admin.entity.OrderEntity;
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

    @Override
    public Customer getCustomerByPhone(String phoneNumber) {
        CustomerEntity customerEntity = customerRepository.findByPhone(phoneNumber);

        if (customerEntity != null) {
            return CustomerConvertor.toModel(customerEntity);
        }
        return null;
    }

    @Override
    public CustomerEntity getCustomerEntityByCustomer(Customer customer) {
        return customerRepository.findById(customer.getId()).orElseThrow();
    }

    @Override
    public void setPoint(CustomerEntity customerEntity, OrderEntity orderEntity) {
        double newPoint = (orderEntity.getCustomerEntity().getPoint() - orderEntity.getPoint()) + (0.02 * orderEntity.getAmount());
        customerEntity.setPoint((int) newPoint);
        customerRepository.save(customerEntity);
    }
}
