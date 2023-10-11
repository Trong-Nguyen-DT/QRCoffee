package com.example.customer.service;


import com.example.customer.domain.Product;
import com.example.customer.entity.ProductEntity;

import java.util.List;


public interface ProductService {

    List<Product> getAllProduct();

    ProductEntity getProductById(Long productId);

    List<Product> getAllProductByCategoryId(Long categoryId);
}
