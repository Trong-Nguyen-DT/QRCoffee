package com.example.admin.service;

import com.example.admin.domain.Category;
import com.example.admin.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();
    void createCategory(Category category);

    CategoryEntity getCategoryById(Long category_id);
}
