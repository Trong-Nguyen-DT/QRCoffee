package com.example.customer.controller;


import com.example.customer.domain.Customer;
import com.example.customer.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private CustomerService customerService;


    @GetMapping()
    public String showErrorQR() {
        return "ErrorQR";
    }

    @GetMapping("/{tb}")
    public String showLogin(@PathVariable String tb, Model model) {
        try {
            Integer banValue = Integer.parseInt(tb);
            model.addAttribute("tableNumber", banValue);
            return "LoginMember";
        } catch (NumberFormatException e) {
            return "ErrorQR";
        }
    }



    @PostMapping("/{tb}")
    public String processLogin(
            @RequestParam("phone") String phoneNumber,
            @PathVariable String tb, // Thay đổi kiểu dữ liệu của 'ban' thành String
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        try {
            Integer banValue = Integer.parseInt(tb);
            Customer customer = customerService.getCustomerByPhone(phoneNumber);
            if (customer != null) {
                session.setAttribute("customer", customer);

                // Chuyển hướng đến /menu/{tb}
                redirectAttributes.addAttribute("tb", banValue);
                return "redirect:/menu/{tb}";
            } else {
                return "redirect:/login";
            }
        } catch (NumberFormatException e) {
            return "redirect:/login";
        }
    }



}
