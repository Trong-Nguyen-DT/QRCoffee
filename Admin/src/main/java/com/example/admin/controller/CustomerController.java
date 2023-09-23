package com.example.admin.controller;


import com.example.admin.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer")

    public String listCustomer(Model model){
        model.addAttribute("customer",customerService.getAllCustomers());

        return "CustomerAdmin";

    }
}
