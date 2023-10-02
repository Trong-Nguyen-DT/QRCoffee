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
    private Long orderCode;
    private Long amount;
    private Long total;
    private Integer point;
    private String description;
    private Long customer_id;
    private String buyerName;
    private String buyerPhone;
    private String returnUrl;
    private String cancelUrl;
    private String signature;
    private Long user_id;
    private Boolean status;
    private List<OrderDetail> items;
    private Long tableId;
}
