package com.example.admin.repository;

import com.example.admin.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {


    List<CustomerEntity> findAll();
}
