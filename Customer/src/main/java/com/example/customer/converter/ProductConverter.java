package com.example.customer.converter;

import com.example.customer.domain.Product;
import com.example.customer.entity.ProductEntity;

public class ProductConverter {

    public static Product toModel(ProductEntity entity) {
        Product product = new Product();
        product.setId(entity.getId());
        product.setTitle(entity.getTitle());
        product.setPrice(entity.getPrice());
        product.setImage(entity.getImage());
        product.setCategory_id(entity.getCategoryEntity().getId());
        return product;
    }
}
