package com.example.customer.domain;


import com.example.customer.converter.CustomerConverter;
import com.example.customer.converter.OrderDetailConverter;
import com.example.customer.entity.OrderDetailEntity;
import com.example.customer.entity.OrderEntity;
import com.example.customer.entity.ProductEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartEntity implements Serializable {

    private OrderEntity orderEntity = new OrderEntity();

    public void addItem(ProductEntity productEntity) {
        Optional<OrderDetailEntity> existingDetail = orderEntity.getOrderDetails().stream()
                .filter(item -> Objects.equals(item.getProductEntity().getId(), productEntity.getId()))
                .findFirst();
        if (existingDetail.isPresent()) {
            existingDetail.get().setQuantity(existingDetail.get().getQuantity() + 1);
        } else {
            OrderDetailEntity detail = new OrderDetailEntity();
            detail.setProductEntity(productEntity);
            detail.setQuantity(1);
            orderEntity.getOrderDetails().add(detail);
        }
    }

    public void reduceItem(ProductEntity productEntity) {

        Optional<OrderDetailEntity> existingDetail = orderEntity.getOrderDetails().stream()
                .filter(item -> Objects.equals(item.getProductEntity().getId(), productEntity.getId()))
                .findFirst();

        if (existingDetail.isPresent()) {
            int newQuantity = existingDetail.get().getQuantity() - 1;
            if (newQuantity > 0) {
                existingDetail.get().setQuantity(newQuantity);
            } else {
                orderEntity.getOrderDetails().remove(existingDetail.get());
            }
        }
    }


    public List<OrderDetail> getAllCartItems() {
        return orderEntity.getOrderDetails().stream().map(OrderDetailConverter::toModel).collect(Collectors.toList());
    }

    public void remove(Long id) {
        OrderDetailEntity detail = Optional.of(orderEntity.getOrderDetails())
                .orElse(new ArrayList<>())
                .stream()
                .filter(item -> Objects.equals(item.getProductEntity().getId(), id))
                .findFirst()
                .orElse(new OrderDetailEntity());

        orderEntity.getOrderDetails().remove(detail);
    }

    public void emptyCart() {
        orderEntity.setOrderDetails(List.of());
    }


    public Long getTotalPrice() {
        Long totalPrice = 0L;
        for (OrderDetailEntity detail : orderEntity.getOrderDetails()) {
            ProductEntity product = detail.getProductEntity();
            int quantity = detail.getQuantity();
            Long price = product.getPrice();
            totalPrice += price * quantity;
        }
        return totalPrice;
    }

    public OrderEntity getOrderEntity() {
        return this.orderEntity;
    }
}
