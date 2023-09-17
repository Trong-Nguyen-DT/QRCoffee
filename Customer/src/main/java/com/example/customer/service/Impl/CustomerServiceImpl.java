package com.example.customer.service.Impl;

import com.example.customer.converter.CustomerConverter;
import com.example.customer.domain.Customer;
import com.example.customer.entity.CustomerEntity;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void createMember(Customer customer) {
        customerRepository.save(CustomerConverter.toEntity(customer));
    }

    @Override
    public Customer getCustomerByPhone(String phoneNumber) {
        CustomerEntity customerEntity = customerRepository.findByPhone(phoneNumber);

        if (customerEntity != null) {
            return CustomerConverter.toModel(customerEntity);
        } else {
            return null;
        }
    }
}
