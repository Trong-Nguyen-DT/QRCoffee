package com.example.admin.controller;


import com.example.admin.domain.OrderHistory;
import com.example.admin.entity.OrderDetailHistoryEntity;
import com.example.admin.repository.OrderHistoryRepository;
import com.example.admin.service.CustomerService;
import com.example.admin.service.OrderDetailService;
import com.example.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("admin")
public class OrderController {

    @Autowired

    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderDetailService orderDetailService;



    @GetMapping("/orders")
    public String showOrderHistory(Model model){
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("customers",customerService.getAllCustomers());

        return "OrderHistory";
    }
    @GetMapping("/orders/search")
    public String searchOrderHistory (@RequestParam("startTime") LocalDateTime startTime,
                                      @RequestParam("endTime") LocalDateTime endTime,Model model){
        List<OrderHistory> orderHistories = orderService.getOrderByTime(startTime,endTime);
        model.addAttribute("orders", orderHistories );
        model.addAttribute("customers",customerService.getAllCustomers());
        return "OrderHistorySearch";
    }

    @GetMapping("orderdetail/{id}")
    public String showOrderDetail(@PathVariable String id, Model model){
        Long orderID = Long.parseLong(id);
        model.addAttribute("orderDetails", orderDetailService.getAllDetailByOrderId(orderID));
        return "OrderHistoryDetail";
    }
}
