package com.example.admin.controller.UserController;


import com.example.admin.domain.Order;
import com.example.admin.domain.Table;
import com.example.admin.service.OrderDetailService;
import com.example.admin.service.OrderService;
import com.example.admin.service.StaffService;
import com.example.admin.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("staff")
public class StaffHomeController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private TableService tableService;


    @GetMapping()
    public String ShowHomePage(Model model){
        // Lấy thông tin xác thực của người dùng hiện tại
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("user", staffService.getUserByUserName(authentication.getName()));
        model.addAttribute("orders", orderService.getOrderConfirmedFalse(false));
        model.addAttribute("tables", tableService.getAllTables());
        model.addAttribute("tablesFalse", tableService.getAllTableFalse());
        model.addAttribute("tableYellow", tableService.getAllTableYellow(orderService.getOrderConfirmedFalse(false)));
        return "Staff/StaffHome";
    }

    @GetMapping("/detail/{id}")
    public String showOrderDetail(@PathVariable Long id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("orders", orderService.getOrderConfirmedFalse(false));
        model.addAttribute("orderDetails", orderDetailService.getItemsById(id));
        model.addAttribute("order", orderService.getOrderById(id));
        model.addAttribute("user", staffService.getUserByUserName(authentication.getName()));
        model.addAttribute("tables", tableService.getAllTables());
        model.addAttribute("tablesFalse", tableService.getAllTableFalse());
        model.addAttribute("tableYellow", tableService.getAllTableYellow(orderService.getOrderConfirmedFalse(false)));
        for (Table table : tableService.getAllTableYellow(orderService.getOrderConfirmedFalse(false))) {
            System.out.println(table.getName());
        }
        return "Staff/StaffHomeDetail";
    }

    @GetMapping("/table/{id}")
    public String showTable(@PathVariable Long id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", staffService.getUserByUserName(authentication.getName()));
        model.addAttribute("table", tableService.getById(id));
        model.addAttribute("orders", orderService.getOrderConfirmedFalse(false));
        model.addAttribute("tables", tableService.getAllTables());
        model.addAttribute("tablesFalse", tableService.getAllTableFalse());
        model.addAttribute("tableYellow", tableService.getAllTableYellow(orderService.getOrderConfirmedFalse(false)));
        return "Staff/StaffHomeDetailYellow";
    }


    @GetMapping("/confirm/{id}")
    public String confirmOrder(@PathVariable String id){
        Long orderId = Long.parseLong(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Order order = orderService.confirmOrder(orderId, staffService.getUserByUserName(authentication.getName()));
        return "redirect:/staff";
    }

    @GetMapping("/checkout/{id}")
    public String checkoutOrder(@PathVariable String id){
        Long tableId = Long.parseLong(id);
        tableService.checkoutTable(tableId);
        return "redirect:/staff";
    }

    @PostMapping("switch-table/{id}")
    public String switchTable(@PathVariable String id, @ModelAttribute Table table) {
        Long tableId = Long.parseLong(id);
        tableService.switchTable(tableId, table.getId());
        return "redirect:/staff";
    }


}
