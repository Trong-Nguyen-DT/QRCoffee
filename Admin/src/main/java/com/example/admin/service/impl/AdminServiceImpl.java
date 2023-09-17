package com.example.admin.service.impl;

import com.example.admin.entity.AdminEntity;
import com.example.admin.repository.AdminRepository;
import com.example.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public AdminEntity checkUsername(String username) {
        AdminEntity adminEntity = adminRepository.findByUsername(username);
        return adminEntity;
    }
}
