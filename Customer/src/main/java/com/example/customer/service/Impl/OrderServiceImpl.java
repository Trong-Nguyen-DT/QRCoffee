package com.example.customer.service.Impl;

import com.example.customer.converter.CustomerConverter;
import com.example.customer.converter.SignatureGenerator;
import com.example.customer.domain.Customer;
import com.example.customer.domain.Order;
import com.example.customer.entity.OrderEntity;
import com.example.customer.entity.OrderHistoryEntity;
import com.example.customer.repository.OrderHistoryRepository;
import com.example.customer.repository.OrderRepository;
import com.example.customer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;


    @Override
    public OrderEntity saveOrder(Integer tb, Customer customer, Long totalPrice, Integer point) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderDateTime(LocalDateTime.now());
        orderEntity.setCustomerEntity(CustomerConverter.toEntity(customer));
        orderEntity.setTb(tb);
        orderEntity.setPoint(point);
        orderEntity.setConfirmed(false);
        orderEntity.setStatus(false);
        orderEntity.setTotalPrice(totalPrice);
        orderEntity.setAmount(totalPrice - point);
        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Override
    public Order setOrder(OrderEntity orderEntity) {
        Order order = new Order();
        order.setOrderCode(orderEntity.getId());
        order.setAmount(orderEntity.getAmount());
        order.setDescription("Bàn số " + orderEntity.getTb());
        order.setBuyerName(orderEntity.getCustomerEntity().getName());
        order.setBuyerPhone(orderEntity.getCustomerEntity().getPhone());
        order.setCancelUrl("http://localhost:8080/payment/" + orderEntity.getTb() + "/failed" + "/" + order.getOrderCode());
        order.setReturnUrl("http://localhost:8080/payment/" + orderEntity.getTb() + "/success"+ "/" + order.getOrderCode());

        Map<String, String> params = Map.of(
                "amount", String.valueOf(order.getAmount()),
                "cancelUrl", order.getCancelUrl(),
                "description", order.getDescription(),
                "orderCode", String.valueOf(order.getOrderCode()),
                "returnUrl", order.getReturnUrl()
        );

        String ChecksumKey = "22ee21ab306b80fac1782bb426e6140498bc4b5b9f483f30d4883f320731e29e";
        String signature = SignatureGenerator.generateSignature(params, ChecksumKey);
        order.setSignature(signature);
        return order;
    }

    @Override
    public OrderHistoryEntity saveOrderHistory(OrderEntity orderEntity) {
        OrderHistoryEntity orderHistoryEntity = new OrderHistoryEntity();
        orderHistoryEntity.setId(orderEntity.getId());
        orderHistoryEntity.setOrderDateTime(orderEntity.getOrderDateTime());
        orderHistoryEntity.setTotalPrice(orderEntity.getTotalPrice());
        orderHistoryEntity.setAmount(orderEntity.getAmount());
        orderHistoryEntity.setPoint(orderEntity.getPoint());
        orderHistoryEntity.setCustomerId(orderEntity.getCustomerEntity().getId());
        return orderHistoryRepository.save(orderHistoryEntity);
    }
}
