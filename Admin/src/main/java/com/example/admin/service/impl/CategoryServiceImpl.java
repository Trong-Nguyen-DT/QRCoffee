package com.example.admin.service.impl;

import com.example.admin.convertor.CategoryConvertor;
import com.example.admin.domain.Category;
import com.example.admin.domain.CategoryData;
import com.example.admin.entity.CategoryEntity;
import com.example.admin.repository.CategoryRepository;
import com.example.admin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll().stream().map(CategoryConvertor::toModel).collect(Collectors.toList());
    }

    @Override
    public void createCategory(Category category) {
        CategoryEntity entity = CategoryConvertor.toEntity(category);
        categoryRepository.save(entity);
    }

    @Override
    public CategoryEntity getCategoryById(Long category_id) {
       return categoryRepository.findById(category_id).orElseThrow();
    }

    @Override
    public List<CategoryData> getAllCategoryData() {
        List<CategoryData> categoryDataList = new ArrayList<>();
        List<CategoryEntity> categoryEntity = categoryRepository.findAll();

        for (CategoryEntity entity: categoryEntity) {
            CategoryData categoryData = new CategoryData();
            categoryData.setCategoryName(entity.getName());



            categoryData.setQuantity(entity.getProductEntities().size());

            categoryDataList.add(categoryData);
        }

        return categoryDataList;
    }
}
