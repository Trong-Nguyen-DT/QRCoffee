package com.example.customer.controller;


import com.example.customer.domain.Customer;
import com.example.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class RegisterController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("register")
    public String showRegister(Model model) {
        model.addAttribute("customer", new Customer());
        return "CreateMember";
    }

    @PostMapping("register")
    public String registerCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.createMember(customer);
        return "redirect:/login";
    }
}
