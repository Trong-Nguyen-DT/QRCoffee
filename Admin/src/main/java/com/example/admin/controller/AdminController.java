package com.example.admin.controller;


import com.example.admin.service.CategoryService;
import com.example.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {


    @GetMapping
    public String showAdminPage() {
        return "AdminPage";
    }
}
