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
public class Product {

    private Long id;

    private String title;

    private Long price;

    private String image;

    private Long category_id;

    private boolean deleted;

    private List<OrderDetail> orderDetails;

}
