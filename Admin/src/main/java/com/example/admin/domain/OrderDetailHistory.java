package com.example.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailHistory {

    private Long id;

    private Long orderId;

    private Long productId;

    private String productTitle;

    private Long productPrice;

    private int quantity;

    private OrderHistory orderHistory;
}
