package com.example.admin.controller.UserController;


import com.example.admin.domain.Order;
import com.example.admin.domain.OrderDetail;
import com.example.admin.service.OrderDetailService;
import com.example.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("staff")
public class StaffHomeController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping()
    public String ShowHomePage(Model model){
        model.addAttribute("orders", orderService.getOrderConfirmedFalse(false));
        return "Staff/StaffHome";
    }

    @GetMapping("/detail/{id}")
    public String showOrderDetail(@PathVariable Long id, Model model){
        model.addAttribute("orders", orderService.getOrderConfirmedFalse(false));
        model.addAttribute("orderDetails", orderDetailService.getItemsById(id));
        model.addAttribute("order", orderService.getOrderById(id));


        return "Staff/StaffHomeDetail";
    }


}
