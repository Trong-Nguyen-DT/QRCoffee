package com.example.customer.controller;


import com.example.customer.domain.Customer;
import com.example.customer.domain.Order;
import com.example.customer.entity.OrderDetailHistoryEntity;
import com.example.customer.service.CustomerService;
import com.example.customer.validator.CustomerValidator;
import com.example.customer.validator.TableValidator;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("my/{tb}")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerValidator customerValidator;

    @Autowired
    private TableValidator tableValidator;

    @GetMapping()
    public String showCustomerInfo(Model model, HttpSession session, @PathVariable String tb){
        Customer customer = customerValidator.checkSession(session);
        Long tableId = tableValidator.validateTable(tb);
        model.addAttribute("customer", customer);
        model.addAttribute("orders", customerService.getAllOrderByCustomerId(customer.getId()));
        model.addAttribute("tb", tableId);
        return "HomeCustomer";
    }

    @GetMapping("{orderId}")
    public String showOrderDetail(@PathVariable String orderId, Model model, HttpSession session, @PathVariable String tb) {
        Customer customer = customerValidator.checkSession(session);
        Long tableId = tableValidator.validateTable(tb);
        Long id = Long.parseLong(orderId);
        model.addAttribute("customer", customer);
        model.addAttribute("orderDetails", customerService.getAllOrderDetailByOrderId(id));
        model.addAttribute("tb", tableId);
        return "HomeCustomerOrderDetail";
    }
}
