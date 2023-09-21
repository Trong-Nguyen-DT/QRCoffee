package com.example.customer.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "OrderHistory")
public class OrderHistoryEntity {

    @Id
    private Long id;

    private LocalDateTime orderDateTime;

    private Long totalPrice;

    private Long amount;

    private Integer point;

    private Long customerId;

    private Long userId;

    @OneToMany(mappedBy = "orderHistoryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetailHistoryEntity> orderDetailHistoryEntities;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderDetailHistoryEntity> getOrderDetailHistoryEntities() {
        return orderDetailHistoryEntities;
    }

    public void setOrderDetailHistoryEntities(List<OrderDetailHistoryEntity> orderDetailHistoryEntities) {
        this.orderDetailHistoryEntities = orderDetailHistoryEntities;
    }
}
