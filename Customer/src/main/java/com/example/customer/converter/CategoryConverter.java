package com.example.customer.converter;

import com.example.customer.domain.Category;
import com.example.customer.entity.CategoryEntity;

import java.util.stream.Collectors;

public class CategoryConverter {

    public static Category toModel(CategoryEntity entity) {
        Category category = new Category();
        category.setId(entity.getId());
        category.setName(entity.getName());
        category.setProducts(entity.getProductEntities().stream().map(ProductConverter::toModel).collect(Collectors.toList()));
        return category;
    }
}
