package com.example.customer.controller;


import com.example.customer.domain.*;
import com.example.customer.entity.ProductEntity;
import com.example.customer.service.CategoryService;
import com.example.customer.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartEntity cartEntity;

    @GetMapping()
    public String showErrorQR() {
        return "ErrorQR";
    }

    @GetMapping("/{tb}")
    public String showMenuPage(Model model, HttpSession session, @PathVariable String tb) {
        try {
            Integer banValue = Integer.parseInt(tb);
            Customer customer = (Customer) session.getAttribute("customer");
            if (customer != null) {
                model.addAttribute("customer", customer);
                model.addAttribute("product", new ProductEntity());
                model.addAttribute("orderDetails", cartEntity.getAllCartItems());
                model.addAttribute("products", productService.getAllProduct());
                model.addAttribute("categories", categoryService.getAllCategory());
                return "Menu";
            } else {
                return "redirect:/login";
            }
        } catch (NumberFormatException e) {
            return "ErrorQR";
        }
    }



    @GetMapping("/{tb}/addItem")
    public String addItem(@RequestParam Long productId, @PathVariable String tb, HttpSession session, RedirectAttributes redirectAttributes) {

        try {
            Integer banValue = Integer.parseInt(tb);
            Customer customer = (Customer) session.getAttribute("customer");
            if (customer != null) {
                ProductEntity product = productService.getProductById(productId);
                cartEntity.addItem(product);
                redirectAttributes.addAttribute("tb", banValue);
                return "redirect:/menu/{tb}";
            } else {
                return "redirect:/login";
            }
        } catch (NumberFormatException e) {
            return "ErrorQR";
        }


    }


    @GetMapping("/{tb}/reduceItem")
    public String reduceItem(@RequestParam Long productId, @PathVariable String tb, RedirectAttributes redirectAttributes, HttpSession session) {

        try {
            Integer banValue = Integer.parseInt(tb);
            Customer customer = (Customer) session.getAttribute("customer");
            if (customer != null) {
                ProductEntity product = productService.getProductById(productId);
                cartEntity.reduceItem(product);
                redirectAttributes.addAttribute("tb", banValue);
                return "redirect:/menu/{tb}";
            } else {
                return "redirect:/login";
            }
        } catch (NumberFormatException e) {
            return "ErrorQR";
        }
    }

}
