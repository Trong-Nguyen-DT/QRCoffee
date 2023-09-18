package com.example.admin.controller;


import com.example.admin.domain.CategoryData;
import com.example.admin.domain.Product;
import com.example.admin.domain.User;
import com.example.admin.remote.RemoteService;
import com.example.admin.service.CategoryService;
import com.example.admin.service.ProductService;
import com.example.admin.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminAPI {

    @Autowired
    private ProductService productService;

    @Autowired
    private RemoteService remoteService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/category-data")
    public ResponseEntity<List<CategoryData>> getCategoryData() {
        List<CategoryData> data = categoryService.getAllCategoryData(); // Lấy dữ liệu từ cơ sở dữ liệu
        return ResponseEntity.ok(data);
    }
}
