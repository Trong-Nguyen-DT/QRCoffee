package com.example.admin.service.impl;

import com.example.admin.convertor.*;
import com.example.admin.domain.Customer;
import com.example.admin.domain.Order;
import com.example.admin.domain.OrderHistory;
import com.example.admin.domain.User;
import com.example.admin.entity.CustomerEntity;
import com.example.admin.entity.OrderEntity;
import com.example.admin.entity.OrderHistoryEntity;
import com.example.admin.entity.TableEntity;
import com.example.admin.repository.OrderHistoryRepository;
import com.example.admin.repository.OrderRepository;
import com.example.admin.repository.TableRepository;
import com.example.admin.repository.UserRepository;
import com.example.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TableRepository tableRepository;


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
        TableEntity tableEntity = tableRepository.findById(orderEntity.getTableEntity().getId()).orElseThrow();
        tableEntity.setStatus(false);
        tableRepository.save(tableEntity);
        OrderHistoryEntity orderHistoryEntity = orderHistoryRepository.findById(id).orElseThrow();
        orderHistoryEntity.setUserId(user.getId());
        orderHistoryRepository.save(orderHistoryEntity);
        orderRepository.save(orderEntity);
    }

    @Override
    public OrderEntity saveOrder(Customer customer, Long totalPrice, int point, User user) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderDateTime(LocalDateTime.now());
        orderEntity.setUserEntity(StaffConvertor.toEntityOrder(user));
        if (customer != null) {
            orderEntity.setCustomerEntity(CustomerConvertor.toEntity(customer));
        }
        orderEntity.setPoint(point);
        orderEntity.setConfirmed(true);
        orderEntity.setStatus(false);
        orderEntity.setTotalPrice(totalPrice);
        orderEntity.setAmount(totalPrice - point);
        return orderRepository.save(orderEntity);
    }

    @Override
    public Order setOrder(OrderEntity orderEntity) {
        Order order = new Order();
        order.setOrderCode(orderEntity.getId());
        order.setAmount(orderEntity.getAmount());
        order.setDescription("Mang về");
        CustomerEntity customerEntity = orderEntity.getCustomerEntity();
        if (customerEntity != null) {
            order.setBuyerName(orderEntity.getCustomerEntity().getName());
            order.setBuyerPhone(orderEntity.getCustomerEntity().getPhone());
        } else {
            order.setBuyerName("Guest");
            order.setBuyerPhone("không có");
        }
        order.setCancelUrl("http://localhost:8081/staff/payment-failed");
        order.setReturnUrl("http://localhost:8081/staff/payment-success/" + order.getOrderCode());

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
    public OrderEntity getOrderEntityById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }

    @Override
    public OrderEntity setStatus(OrderEntity orderEntity) {
        orderEntity.setStatus(true);
        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderHistoryEntity saveOrderHistory(OrderEntity orderEntity) {
        OrderHistoryEntity orderHistoryEntity = new OrderHistoryEntity();
        orderHistoryEntity.setId(orderEntity.getId());
        orderHistoryEntity.setOrderDateTime(orderEntity.getOrderDateTime());
        orderHistoryEntity.setTotalPrice(orderEntity.getTotalPrice());
        orderHistoryEntity.setAmount(orderEntity.getAmount());
        orderHistoryEntity.setPoint(orderEntity.getPoint());
        orderHistoryEntity.setUserId(orderEntity.getUserEntity().getId());
        if (orderEntity.getCustomerEntity() != null) {
            orderHistoryEntity.setCustomerId(orderEntity.getCustomerEntity().getId());
        }
        return orderHistoryRepository.save(orderHistoryEntity);
    }

}
