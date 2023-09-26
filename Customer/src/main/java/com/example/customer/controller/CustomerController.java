package com.example.customer.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("my")
public class CustomerController {

    @GetMapping("info")
    public String showCustomerInfo(){


        return "HomeCustomer";
    }

    @GetMapping("order-history")
    public String showCustomerOrder(){


        return "HomeCustomer";
    }
}
