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
@RequestMapping("menu/{tb}")
public class MenuController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartEntity cartEntity;

    @GetMapping("")
    public String showMenuPage(Model model, HttpSession session, @PathVariable String tb) {
        model.addAttribute("orderdetails", cartEntity.getAllCartItems());
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("categories", categoryService.getAllCategory());
        for (OrderDetail orderDetail: cartEntity.getAllCartItems()) {
            System.out.println(orderDetail.getProduct_id());
            System.out.println(orderDetail.getQuantity());
            System.out.println("=========");
        }

        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            model.addAttribute("customer", customer);
            model.addAttribute("product", new ProductEntity());
            return "Menu";
        } else {
            return "redirect:/login";
        }

//        model.addAttribute("customer", customer);
//        model.addAttribute("product", new ProductEntity());
//        return "Menu";
    }



    @GetMapping("/addItem")
    public String addItem(@RequestParam Long productId, @PathVariable String tb, HttpSession session, RedirectAttributes redirectAttributes) {
        Integer banValue = Integer.parseInt(tb);
        Customer customer = (Customer) session.getAttribute("customer");

        ProductEntity product = productService.getProductById(productId);
        cartEntity.addItem(product, customer);
        redirectAttributes.addAttribute("tb", banValue);
        return "redirect:/menu/{tb}";
    }


    @GetMapping("/reduceItem")
    public String reduceItem(@RequestParam Long productId, @PathVariable String tb, RedirectAttributes redirectAttributes) {
        Integer banValue = Integer.parseInt(tb);
        ProductEntity product = productService.getProductById(productId);
        cartEntity.reduceItem(product);
        redirectAttributes.addAttribute("tb", banValue);
        return "redirect:/menu/{tb}";
    }

}
