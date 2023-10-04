package com.example.customer.controller;


import com.example.customer.domain.Customer;
import com.example.customer.service.CustomerService;
import com.example.customer.validator.TableValidator;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping()
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TableValidator tableValidator;



    @GetMapping()
    public String showErrorQR() {
        return "ErrorQR";
    }

    @GetMapping("login")
    public String showErrorLogin() {
        return "ErrorQR";
    }

    @GetMapping("login/{tb}")
    public String showLogin(@PathVariable String tb, RedirectAttributes redirectAttributes) {
        Long tableId = tableValidator.validateTable(tb);
        redirectAttributes.addAttribute("tb", tableId);
        return "LoginMember";
    }



    @PostMapping("login/{tb}")
    public String processLogin(
            @RequestParam("phone") String phoneNumber,
            @PathVariable String tb,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        Long tableId = tableValidator.validateTable(tb);
        Customer customer = customerService.getCustomerByPhone(phoneNumber);
        session.setAttribute("customer", customer);
        // Chuyển hướng đến /menu/{tb}
        redirectAttributes.addAttribute("tb", tableId);
        return "redirect:/menu/{tb}";
    }



}
