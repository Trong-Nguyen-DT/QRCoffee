package com.example.admin.controller.UserController;


import com.example.admin.domain.CartEntity;
import com.example.admin.entity.ProductEntity;
import com.example.admin.service.CategoryService;
import com.example.admin.service.ProductService;
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
@RequestMapping("staff/menu")
public class StaffOrderController {

    @Autowired
    private CartEntity cartEntity;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public String showMenu(Model model){
        model.addAttribute("product", new ProductEntity());
        model.addAttribute("orderDetails", cartEntity.getAllCartItems());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "Staff/MenuStaff";
    }

    @GetMapping("/addItem")
    public String addItem(@RequestParam Long productId) {
        ProductEntity product = productService.getProductEntityById(productId);
        cartEntity.addItem(product);
        return "redirect:/staff/menu";
    }

    @GetMapping("/reduceItem")
    public String reduceItem(@RequestParam Long productId) {
        ProductEntity product = productService.getProductEntityById(productId);
        cartEntity.reduceItem(product);
        return "redirect:/staff/menu";

    }
}
