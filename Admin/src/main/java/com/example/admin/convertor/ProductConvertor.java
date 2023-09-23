package com.example.admin.convertor;

import com.example.admin.domain.Product;
import com.example.admin.entity.CategoryEntity;
import com.example.admin.entity.OrderDetailHistoryEntity;
import com.example.admin.entity.ProductEntity;

import java.util.List;


public class ProductConvertor {

   public static Product toModel(ProductEntity productEntity)
   {
          Product product = new Product();
          product.setId(productEntity.getId());
          product.setCategory_id(productEntity.getCategoryEntity().getId());
          product.setImage(productEntity.getImage());
          product.setPrice(productEntity.getPrice());
          product.setTitle(productEntity.getTitle());
          product.setDeleted(productEntity.getDeleted());
          return product;

   }
   public static ProductEntity toEntity(Product product, CategoryEntity categoryEntity){
         ProductEntity entity = new ProductEntity();
       entity.setTitle(product.getTitle());
       entity.setPrice(product.getPrice());

        entity.setCategoryEntity(categoryEntity);
//        entity.setImage(product.getImage());


        entity.setDeleted(true);

         return entity;

   }
}
