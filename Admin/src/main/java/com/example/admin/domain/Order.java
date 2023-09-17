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
public class Order {
    private Long id;

    private LocalDateTime orderDateTime;

    private Customer customer;

    private User user;

    private List<OrderDetail> orderDetails;

}
