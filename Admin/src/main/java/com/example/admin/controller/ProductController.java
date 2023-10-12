package com.example.admin.controller;

import com.example.admin.domain.Category;
import com.example.admin.domain.Product;
import com.example.admin.service.CategoryService;
import com.example.admin.service.ProductService;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("admin")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
     private CategoryService categoryService;

    @Value("${imagePath}")
    private String imagePath;

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
    public String createProduct(@ModelAttribute Product product, @RequestParam("file") MultipartFile file, Model model) throws IOException, URISyntaxException {
        String message = "";

        try {
//            getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
//            String fileName = UUID.randomUUID().toString() + "." + fileExtension; // Tạo tên file mới để tránh trùng lặp

            File file1 = new File( imagePath + file.getOriginalFilename());


            try (OutputStream os = new FileOutputStream(file1)) {
                os.write(file.getBytes());
            }

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            model.addAttribute("message", message);
//            model.addAttribute("fileExtension", fileExtension);
//            model.addAttribute("fileDownloadLink", "/files/download/" + fileExtension);

        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            model.addAttribute("message", message);
        }
        product.setImage(file.getOriginalFilename());
        productService.saveProduct(product);
        return "redirect:/admin/product";
    }

//    private boolean getFileExtension(String originalFilename) {
//        String newFileName = "png";
//        //hack.png
//        String[] fileSplits = originalFilename.split("\\."); // ["hack", "png"]
//        return newFileName.equals(fileSplits[fileSplits.length - 1]);
//    }

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
