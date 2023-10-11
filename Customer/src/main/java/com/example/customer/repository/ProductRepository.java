package com.example.customer.repository;

import com.example.customer.entity.CategoryEntity;
import com.example.customer.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByCategoryEntity(CategoryEntity categoryEntity);
}
