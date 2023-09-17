package com.example.customer.converter;

import com.example.customer.domain.Customer;
import com.example.customer.entity.CustomerEntity;

public class CustomerConverter {

    public static Customer toModel (CustomerEntity customerEntity) {
        Customer customer = new Customer();
        customer.setId(customerEntity.getId());
        customer.setPhone(customerEntity.getPhone());
        customer.setPoint(customerEntity.getPoint());
        customer.setName(customerEntity.getName());
//        customer.setOrders(customerEntity.getOrderEntities());
        return customer;

    }

    public static CustomerEntity toEntity(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setName(customer.getName());
        entity.setPhone(customer.getPhone());
        entity.setPoint(0);
        return entity;
    }
}
