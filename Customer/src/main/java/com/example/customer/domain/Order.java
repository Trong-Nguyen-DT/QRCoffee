package com.example.customer.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long orderCode;
    private Long amount;
    private String description;
    private Long customer_id;
    private String buyerName;
    private String buyerPhone;
    private String returnUrl;
    private String cancelUrl;
    private String signature;
    private Long user_id;
    private List<OrderDetail> items;

}
