package com.example.admin.controller;


import com.example.admin.domain.OrderHistory;
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
        List<OrderHistory> orderThisMonth = orderService.getOrderByMonth(
                LocalDateTime.now().getMonth().getValue(), LocalDateTime.now().getYear());
        List<OrderHistory> orderLastMonth = orderService.getOrderByMonth(
                LocalDateTime.now().getMonth().getValue() - 1, LocalDateTime.now().getYear());
        model.addAttribute("quantityOrder", orderThisMonth.size());

        Long totalThisMonth = orderService.getTotalAmountByOrder(orderThisMonth);
        String formattedTotal = String.format("%,d", totalThisMonth);
        model.addAttribute("totalAmount", formattedTotal);

        Long totalLastMonth = orderService.getTotalAmountByOrder(orderLastMonth);
        double percent = orderService.getPercentCompare(totalThisMonth, totalLastMonth);

        model.addAttribute("percent", percent);

        model.addAttribute("topProduct", orderDetailService.getTop4Product());
        model.addAttribute("products", productService.getAllProducts());
        return "AdminPage";
    }
}
