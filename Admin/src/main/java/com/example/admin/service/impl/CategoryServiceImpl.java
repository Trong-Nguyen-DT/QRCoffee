package com.example.admin.service.impl;

import com.example.admin.convertor.CategoryConvertor;
import com.example.admin.domain.Category;
import com.example.admin.domain.CategoryData;
import com.example.admin.entity.CategoryEntity;
import com.example.admin.entity.OrderDetailEntity;
import com.example.admin.entity.OrderDetailHistoryEntity;
import com.example.admin.entity.ProductEntity;
import com.example.admin.repository.*;
import com.example.admin.service.CategoryService;
import com.example.admin.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderDetailHistoryRepository orderDetailHistoryRepository;

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
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<OrderDetailHistoryEntity> orderDetailHistoryEntities = orderDetailHistoryRepository.findAll();


        for (CategoryEntity categoryEntity: categoryEntities) {
            CategoryData categoryData = new CategoryData();
            categoryData.setQuantity(0);
            for (OrderDetailHistoryEntity productEntity : orderDetailHistoryEntities){
                if (categoryEntity.getId() == productRepository.findById(productEntity.getProductId()).orElseThrow().getCategoryEntity().getId()){
                    categoryData.setCategoryName(categoryEntity.getName());
                    categoryData.setQuantity(categoryData.getQuantity() + productEntity.getQuantity());
                }
            }
            categoryDataList.add(categoryData);
        }
        return categoryDataList;
    }

}
