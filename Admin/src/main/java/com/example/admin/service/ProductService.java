package com.example.admin.service;

import com.example.admin.domain.Product;
import com.example.admin.entity.CategoryEntity;
import com.example.admin.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    CategoryEntity getCategoryById(Long id);

     ProductEntity saveProduct(Product product);
    Product getProductById(Long id);

    void updateProduct( Product product);
    void deleteProduct(Long id);

    void restoreProduct(Long id);

    List<Product> getProductsByCategory(Long categoryId);
}
