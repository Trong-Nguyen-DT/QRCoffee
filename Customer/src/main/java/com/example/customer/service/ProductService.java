package com.example.customer.service;


import com.example.customer.domain.Product;
import com.example.customer.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<Product> getAllProduct();

    ProductEntity getProductById(Long productId);
}
