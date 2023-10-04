package com.example.customer.controller;


import com.example.customer.domain.Customer;
import com.example.customer.service.CustomerService;
import com.example.customer.validator.CustomerValidator;
import com.example.customer.validator.TableValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("register/{tb}")
public class RegisterController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerValidator customerValidator;

    @Autowired
    private TableValidator tableValidator;


    @GetMapping()
    public String showRegister(Model model, @PathVariable String tb) {
        model.addAttribute("customer", new Customer());
        return "CreateMember";
    }

    @PostMapping()
    public String registerCustomer(@ModelAttribute("customer") Customer customer, BindingResult bindingResult, @PathVariable String tb, RedirectAttributes redirectAttributes){
        customerValidator.validate(customer, bindingResult);
        Long tableId = tableValidator.validateTable(tb);
        if (bindingResult.hasErrors()) {
            return "redirect:/register/{tb}"; // Trả về trang form đăng ký với thông báo lỗi
        }

        if (customerService.getCustomerByPhone(customer.getPhone()) != null) {
            return "redirect:/register/{tb}";
        }
        customerService.createMember(customer);
        redirectAttributes.addAttribute("tb", tableId);
        return "redirect:/login/{tb}";
    }
}
