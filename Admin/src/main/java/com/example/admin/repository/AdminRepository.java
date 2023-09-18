package com.example.admin.repository;

import com.example.admin.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long>{
    AdminEntity findByUsername(String username);
}
