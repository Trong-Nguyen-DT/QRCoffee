package com.example.customer.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "OrderDetailHistory")
public class OrderDetailHistoryEntity {

    @Id
    private Long id;

    private Long productId;

    private String productTitle;

    private Long productPrice;

    private int quantity;


    @ManyToOne
    @JoinColumn(name = "orderHistoryId")
    private OrderHistoryEntity orderHistoryEntity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderHistoryEntity getOrderHistoryEntity() {
        return orderHistoryEntity;
    }

    public void setOrderHistoryEntity(OrderHistoryEntity orderHistoryEntity) {
        this.orderHistoryEntity = orderHistoryEntity;
    }
}
