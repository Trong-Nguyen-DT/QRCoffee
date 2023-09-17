package com.example.admin.repository;

import com.example.admin.entity.CategoryEntity;
import com.example.admin.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <ProductEntity, Long> {

    List<ProductEntity> findProductEntityByCategoryEntity(CategoryEntity categoryEntity);
}
