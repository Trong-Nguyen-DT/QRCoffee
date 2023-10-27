package com.example.customer.controller;


import com.example.customer.domain.Customer;
import com.example.customer.service.CustomerService;
import com.example.customer.validator.CustomerValidator;
import com.example.customer.validator.TableValidator;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping()
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TableValidator tableValidator;

    @Autowired
    private CustomerValidator customerValidator;



    @GetMapping()
    public String showErrorQR() {
        return "ErrorQR";
    }

    @GetMapping("login")
    public String showErrorLogin() {
        return "ErrorQR";
    }

    @GetMapping("login/{tb}")
    public String showLogin(@PathVariable String tb, Model model) {
        Long tableId = tableValidator.validateTable(tb);
        model.addAttribute("tb", tableId);
        return "LoginMember";
    }



    @PostMapping("login/{tb}")
    public String processLogin(
            @RequestParam("phone") String phoneNumber,
            @PathVariable String tb,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model

    ) {
        Long tableId = tableValidator.validateTable(tb);
        if (phoneNumber.length() != 10) {
            redirectAttributes.addAttribute("errorMessage", "Số điện thoại không hợp lệ. Vui lòng nhập 10 số.");
            return "redirect:/login/{tb}"; // Trả về trang đăng nhập với thông báo lỗi
        }


        Customer customerPhone = customerService.getCustomerByPhone(phoneNumber);
        if (customerPhone !=null) {
            session.setAttribute("customer", customerPhone);
            // Chuyển hướng đến /menu/{tb}
            redirectAttributes.addAttribute("tb", tableId);
            return "redirect:/menu/{tb}";
        } else {
            model.addAttribute("customer", new Customer());
            model.addAttribute("phone", phoneNumber);
            return "CreateMember";
        }
    }

    @PostMapping("register/{tb}")
    public String registerCustomer(
            @ModelAttribute("customer") Customer customer,
            @RequestParam("phone") String phoneNumber,
            BindingResult bindingResult,
            @PathVariable String tb,
            RedirectAttributes redirectAttributes){


        customer.setPhone(phoneNumber);
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

    @GetMapping("exit/{tb}")
    public String exitOrder(HttpSession session, @PathVariable String tb) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login/{tb}";
    }


}
