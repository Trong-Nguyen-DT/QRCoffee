package com.example.admin.controller;


import com.example.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ProductService productService;


    @GetMapping
    public String showAdminPage(Model model) {
        model.addAttribute("quantityCustomer", customerService.getAllCustomers().size());
        model.addAttribute("quantityOrder", orderService.getAllOrders().size());
        Long total = orderService.getAllTotalByTime(LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), 1, 0, 0), LocalDateTime.now());
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedTotal = decimalFormat.format(total);
        model.addAttribute("totalAmount", formattedTotal);
        model.addAttribute("topProduct", orderDetailService.getTop4Product());
        model.addAttribute("products", productService.getAllProducts());


        return "AdminPage";
    }
}
