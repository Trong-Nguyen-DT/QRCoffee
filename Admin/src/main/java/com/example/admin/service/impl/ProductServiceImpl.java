package com.example.admin.service.impl;

import com.example.admin.domain.Product;
import com.example.admin.entity.CategoryEntity;
import com.example.admin.entity.ProductEntity;
import com.example.admin.repository.CategoryRepository;
import com.example.admin.repository.ProductRepository;
import com.example.admin.service.CategoryService;
import com.example.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.admin.convertor.ProductConvertor;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductConvertor::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryEntity getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public ProductEntity saveProduct(Product product) {
        return productRepository.save(ProductConvertor.toEntity(product,
                categoryService.getCategoryById(product.getCategory_id())));
    }

    @Override
    public Product getProductById(Long id) {
        ProductEntity productEntity = productRepository
                .findById(id).orElseThrow(() ->
                        new RuntimeException("Không tìm thấy sản phẩm"));
        return ProductConvertor.toModel(productEntity);
    }

    @Override
    public void updateProduct(Product product) {
        ProductEntity existingProduct = productRepository.findById(product.getId()).orElseThrow(() ->
                new RuntimeException("Không tìm thấy sản phẩm"));

        // Cập nhật thông tin sản phẩm với dữ liệu mới từ form chỉnh sửa
        existingProduct.setTitle(product.getTitle());
        existingProduct.setPrice(product.getPrice());
//        existingProduct.setCategoryEntity(categoryService.getCategoryById(product.getCategory_id()));
        productRepository.save(existingProduct);
        // Lưu sản phẩm đã cập nhật vào cơ sở dữ liệu
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity existingProduct = productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Không tìm thấy sản phẩm"));
        existingProduct.setDeleted(true);
        productRepository.save(existingProduct);
    }

    @Override
    public void restoreProduct(Long id) {
        ProductEntity existingProduct = productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Không tìm thấy sản phẩm"));
        existingProduct.setDeleted(false);
        productRepository.save(existingProduct);
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findProductEntityByCategoryEntity(categoryRepository.findById(categoryId).orElseThrow()).stream().map(ProductConvertor::toModel).toList();
    }

    @Override
    public ProductEntity getProductEntityById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->new RuntimeException("Không tìm thấy sản phẩm"));
    }


}
