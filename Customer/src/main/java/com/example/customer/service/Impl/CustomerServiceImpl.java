package com.example.customer.service.Impl;

import com.example.customer.converter.CustomerConverter;
import com.example.customer.converter.OrderConverter;
import com.example.customer.domain.Customer;
import com.example.customer.domain.Order;
import com.example.customer.entity.CustomerEntity;
import com.example.customer.entity.OrderDetailHistoryEntity;
import com.example.customer.entity.OrderEntity;
import com.example.customer.entity.OrderHistoryEntity;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.repository.OrderDetailHistoryRepository;
import com.example.customer.repository.OrderHistoryRepository;
import com.example.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private OrderDetailHistoryRepository orderDetailHistoryRepository;

    @Override
    public void createMember(Customer customer) {
        customerRepository.save(CustomerConverter.toEntity(customer));
    }

    @Override
    public Customer getCustomerByPhone(String phoneNumber) {
        CustomerEntity customerEntity = customerRepository.findByPhone(phoneNumber);

        if (customerEntity != null) {
            return CustomerConverter.toModel(customerEntity);
        }
        return null;
    }

    @Override
    public void setPoint(OrderEntity orderEntity) {
        CustomerEntity customerEntity = customerRepository.findById(orderEntity.getCustomerEntity().getId()).orElseThrow();
        double newPoint = (orderEntity.getCustomerEntity().getPoint() - orderEntity.getPoint()) + (0.02 * orderEntity.getAmount());
        customerEntity.setPoint((int) newPoint);
        customerRepository.save(customerEntity);
    }

    @Override
    public List<OrderHistoryEntity> getAllOrderByCustomerId(Long id) {
        return orderHistoryRepository.findAllByCustomerId(id);
    }

    @Override
    public List<OrderDetailHistoryEntity> getAllOrderDetailByOrderId(Long id) {
        return orderDetailHistoryRepository.findAllByOrderHistoryEntity(orderHistoryRepository.findById(id).orElseThrow());
    }
}
