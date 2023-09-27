package com.example.admin.service.impl;

import com.example.admin.convertor.AmountData;
import com.example.admin.convertor.OrderConvertor;
import com.example.admin.convertor.OrderHistoryConvertor;
import com.example.admin.domain.Order;
import com.example.admin.domain.OrderHistory;
import com.example.admin.domain.User;
import com.example.admin.entity.OrderEntity;
import com.example.admin.entity.OrderHistoryEntity;
import com.example.admin.repository.OrderHistoryRepository;
import com.example.admin.repository.OrderRepository;
import com.example.admin.repository.UserRepository;
import com.example.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<Order> getOrderConfirmedFalse(Boolean confirmed) {
        return orderRepository.findOrderEntityByConfirmed(confirmed).stream().map(OrderConvertor::toModel).toList();
    }

    @Override
    public Order getOrderById(Long id) {
        return OrderConvertor.toModel(orderRepository.findById(id).orElseThrow());
    }

    @Override
    public List<OrderHistory> getAllOrders() {
        return  orderHistoryRepository.findAll()
                .stream().map(OrderHistoryConvertor::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderHistory> getOrderByTime(LocalDateTime startTime, LocalDateTime endTime) {
        return orderHistoryRepository.findOrderHistoryEntitiesByOrderDateTimeBetween(startTime, endTime).stream().map(OrderHistoryConvertor::toModel).toList();
    }

    @Override
    public Long getAllTotalByTime(LocalDateTime start, LocalDateTime end) {
        List<OrderHistory> orderHistories = orderHistoryRepository.findOrderHistoryEntitiesByOrderDateTimeBetween(start, end).stream().map(OrderHistoryConvertor::toModel).toList();
        Long totalAmount = 0L;
        for (OrderHistory orderHistory : orderHistories){
            totalAmount += orderHistory.getAmount();
        }

        return totalAmount;
    }

    @Override
    public List<AmountData> getAmountByMonth() {
        List<AmountData> amountDataList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            AmountData amountData = new AmountData();
            List<OrderHistoryEntity> orderHistoryEntities = orderHistoryRepository.findOrdersByMonthAndYear(i, LocalDateTime.now().getYear());
            Long amountMonth = 0L;
            for (OrderHistoryEntity orderHistoryEntity: orderHistoryEntities) {
                amountMonth += orderHistoryEntity.getAmount();

            }
            amountData.setAmount(amountMonth != null ? amountMonth : 0L);
            amountData.setMonth(String.valueOf(i));
            amountDataList.add(amountData);
        }



        return amountDataList;
    }

    @Override
    public void confirmOrder(Long id, User user) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow();
        orderEntity.setConfirmed(true);
        orderEntity.setUserEntity(userRepository.findById(user.getId()).orElseThrow());

        OrderHistoryEntity orderHistoryEntity = orderHistoryRepository.findById(id).orElseThrow();
        orderHistoryEntity.setUserId(user.getId());
        orderHistoryRepository.save(orderHistoryEntity);
        orderRepository.save(orderEntity);
    }

}
