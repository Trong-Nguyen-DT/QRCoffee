package com.example.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;

    private String title;

    private Long price;

    private String image;

    private Long category_id;

    private Boolean deleted;

    private List<OrderDetail> orderDetails;


}
