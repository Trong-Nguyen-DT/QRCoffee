package com.example.customer.controller;


import com.example.customer.domain.Customer;
import com.example.customer.domain.Order;
import com.example.customer.entity.OrderDetailHistoryEntity;
import com.example.customer.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("my")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public String showCustomerInfo(Model model, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        model.addAttribute("customer", customer);
        model.addAttribute("orders", customerService.getAllOrderByCustomerId(customer.getId()));
        return "HomeCustomer";
    }

    @GetMapping("{orderId}")
    public String showOrderDetail(@PathVariable String orderId, Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        Long id = Long.parseLong(orderId);
        model.addAttribute("customer", customer);
        model.addAttribute("orderDetails", customerService.getAllOrderDetailByOrderId(id));
        return "HomeCustomerOrderDetail";
    }
}
