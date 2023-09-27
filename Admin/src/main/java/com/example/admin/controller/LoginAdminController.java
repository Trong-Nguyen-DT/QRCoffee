package com.example.admin.controller;

import com.example.admin.entity.UserEntity;
import com.example.admin.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginAdminController {

    @GetMapping("/login")
    public String loginAdmin(Model model) {
        model.addAttribute("user", new UserEntity());
        return "Login";
    }


}
