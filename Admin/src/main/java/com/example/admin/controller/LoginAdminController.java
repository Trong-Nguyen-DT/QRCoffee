package com.example.admin.controller;

import com.example.admin.entity.UserEntity;
import com.example.admin.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginAdminController {


   private final AdminService adminService;


    public LoginAdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/login")
    public String loginAdmin(Model model) {
        model.addAttribute("user", new UserEntity());
        return "Login";
    }


}
