package com.example.customer.service.Impl;

import com.example.customer.converter.ProductConverter;
import com.example.customer.domain.Product;
import com.example.customer.entity.ProductEntity;
import com.example.customer.repository.ProductRepository;
import com.example.customer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll().stream().map(ProductConverter::toModel).toList();
    }

    @Override
    public ProductEntity getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }
}
