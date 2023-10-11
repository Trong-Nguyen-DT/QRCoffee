package com.example.customer.controller;


import com.example.customer.domain.*;
import com.example.customer.entity.ProductEntity;
import com.example.customer.service.CategoryService;
import com.example.customer.service.CustomerService;
import com.example.customer.service.ProductService;
import com.example.customer.validator.CustomerValidator;
import com.example.customer.validator.TableValidator;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartEntity cartEntity;

    @Autowired
    private TableValidator tableValidator;

    @Autowired
    private CustomerValidator customerValidator;

    @GetMapping()
    public String showErrorQR() {
        return "ErrorCustomer";
    }

    @GetMapping("/{tb}")
    public String showMenuPage(Model model, HttpSession session, @PathVariable String tb) {
        Long tableId = tableValidator.validateTable(tb);
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/login";
        }
        model.addAttribute("customer", customer);
        model.addAttribute("orderDetails", cartEntity.getAllCartItems());
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "Menu";
    }

    @GetMapping("{tb}/{id}")
    public String showMenuOneCategory(@PathVariable String tb, Model model, @PathVariable String id, HttpSession session) {
        Long categoryId = Long.parseLong(id);
        model.addAttribute("orderDetails", cartEntity.getAllCartItems());
        model.addAttribute("products", productService.getAllProductByCategoryId(categoryId));
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("category", categoryService.getCategoryById(categoryId));
        return "MenuOneCategory";
    }

    @GetMapping("/{tb}/addItem")
    public String addItem(@RequestParam Long productId, @PathVariable String tb, HttpSession session, RedirectAttributes redirectAttributes) {

        Long tableId = tableValidator.validateTable(tb);
        customerValidator.checkSession(session);
        ProductEntity product = productService.getProductById(productId);
        cartEntity.addItem(product);
        redirectAttributes.addAttribute("tb", tableId);
        return "redirect:/menu/{tb}";
    }

    @GetMapping("/{tb}/reduceItem")
    public String reduceItem(@RequestParam Long productId, @PathVariable String tb, RedirectAttributes redirectAttributes, HttpSession session) {
        Long tableId = tableValidator.validateTable(tb);
        customerValidator.checkSession(session);
        ProductEntity product = productService.getProductById(productId);
        cartEntity.reduceItem(product);
        redirectAttributes.addAttribute("tb", tableId);
        return "redirect:/menu/{tb}";
    }

}
