package com.example.customer.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    private Long id;

    private Long order_id;

    private Long product_id;

    private int quantity;
}
