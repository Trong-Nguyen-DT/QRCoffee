package com.example.admin.controller;

import com.example.admin.domain.Category;
import com.example.admin.domain.Product;
import com.example.admin.service.CategoryService;
import com.example.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
     private CategoryService categoryService;

    @GetMapping("/product")
    public String listProduct(Model model){
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCategory());
          return "ProductAdmin";
    }

    @GetMapping("/product/create")
    public String showProduct(Model model){
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("product", new Product()); // Thay vì new ProductController()
        return "CreateProduct";
    }

    @PostMapping("product/create")
    public String createProduct(@ModelAttribute Product product){
        productService.saveProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/product/edit/{id}")
    public String showUpdate(@PathVariable Long id,  Model model) {
        System.out.println(id);
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("categories", categoryService.getAllCategory());

        return "EditProduct"; // Chuyển hướng về trang danh sách sản phẩm sau khi cập nhật
    }
    @PostMapping("/product/edit")
    public String update(@ModelAttribute Product product){
        productService.updateProduct(product);
        return "redirect:/admin/product";
    }
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id,  Model model) {
        productService.deleteProduct(id);

        return "redirect:/admin/product"; // Chuyển hướng về trang danh sách sản phẩm sau khi cập nhật
    }
    @GetMapping("/product/restore/{id}")
    public String restoreProduct(@PathVariable Long id,  Model model) {
        productService.restoreProduct(id);
        return "redirect:/admin/product/restore"; // Chuyển hướng về trang danh sách sản phẩm sau khi cập nhật
    }
@GetMapping("product/restore")
    public String showRestoreProduct(Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("RestoreProducts", productService.getAllProducts());
        return "RestoreProduct";
}

}
