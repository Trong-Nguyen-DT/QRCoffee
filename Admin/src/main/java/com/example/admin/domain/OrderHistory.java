package com.example.admin.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistory {

    private Long id;

    private LocalDateTime orderDateTime;

    private Long totalPrice;

    private Long amount;

    private Long customerId;

    private Integer point;

    private Long userId;

    private List<OrderDetailHistory> orderDetailHistories;
}
