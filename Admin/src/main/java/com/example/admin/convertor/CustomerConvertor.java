package com.example.admin.convertor;

import com.example.admin.domain.Customer;
import com.example.admin.entity.CustomerEntity;

public class CustomerConvertor {

    public static Customer toModel(CustomerEntity customerEntity){

        Customer customer = new Customer();
        customer.setId(customerEntity.getId());
        customer.setName(customerEntity.getName());
        customer.setPoint(customerEntity.getPoint());
        customer.setPhone(customerEntity.getPhone());
        return customer;

    }

    public static CustomerEntity toEntity(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(customer.getId());
        entity.setName(customer.getName());
        entity.setPhone(customer.getPhone());
        entity.setPoint(0);
        return entity;
    }
}
