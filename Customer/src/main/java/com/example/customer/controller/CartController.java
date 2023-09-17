package com.example.customer.controller;


import com.example.customer.domain.CartEntity;
import com.example.customer.domain.Customer;
import com.example.customer.domain.Product;
import com.example.customer.entity.ProductEntity;
import com.example.customer.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("cart/{tb}")
public class CartController {

    @Autowired
    private CartEntity cartEntity;

    @Autowired
    private ProductService productService;



    @GetMapping()
    public String showProductByCart(Model model, HttpSession session, @PathVariable String tb) {
        Customer customer = (Customer) session.getAttribute("customer");
        model.addAttribute("customer", customer);
        model.addAttribute("orderdetails", cartEntity.getAllCartItems());
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("totalPrice", cartEntity.getTotalPrice());
        return "Cart";
    }

    @GetMapping("/removeItem")
    public String removeItem(@RequestParam Long productId, @PathVariable String tb, RedirectAttributes redirectAttributes) {
        Integer banValue = Integer.parseInt(tb);
        cartEntity.remove(productId);
        redirectAttributes.addAttribute("tb", banValue);
        return "redirect:/cart/{tb}";
    }

    @GetMapping("/emptyItem")
    public String emptyItem(@PathVariable String tb, RedirectAttributes redirectAttributes) {
        Integer banValue = Integer.parseInt(tb);
        cartEntity.emptyCart();
        redirectAttributes.addAttribute("tb", banValue);
        return "redirect:/cart/{tb}";
    }

}
