package com.example.admin.domain;


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
    private String name;
    private long price;
    private int quantity;
}
