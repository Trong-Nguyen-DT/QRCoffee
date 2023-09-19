package com.example.admin.service.impl;

import com.example.admin.convertor.ProductConvertor;
import com.example.admin.convertor.StaffConvertor;
import com.example.admin.domain.Product;
import com.example.admin.domain.User;
import com.example.admin.entity.ProductEntity;
import com.example.admin.entity.UserEntity;
import com.example.admin.repository.UserRepository;
import com.example.admin.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> getAllUsers(String staff) {

        return userRepository.findAllByRole(staff).stream()
                .map(StaffConvertor::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void createStaff(User user) {
        userRepository.save(StaffConvertor.toEntity(user));
    }

    @Override
    public User getStaffById(Long id) {
        UserEntity userEntity = userRepository
                .findById(id).orElseThrow(() ->
                        new RuntimeException("Không tìm thấy sản phẩm"));
        return StaffConvertor.toModel(userEntity);
    }

    @Override
    public void updateStaff(User user) {
        UserEntity existingStaff = userRepository.findById(user.getId()).orElseThrow(() ->
                new RuntimeException("Không tìm thấy sản phẩm"));

        // Cập nhật thông tin sản phẩm với dữ liệu mới từ form chỉnh sửa
        existingStaff.setName(user.getName());
        existingStaff.setAddress(user.getAddress());
        existingStaff.setBirthday(user.getBirthday());
        existingStaff.setPassword(user.getPassword());
        existingStaff.setSalary(user.getSalary());
        existingStaff.setUsername(user.getUsername());
        existingStaff.setPhone(user.getPhone());

//        existingProduct.setCategoryEntity(categoryService.getCategoryById(product.getCategory_id()));
        userRepository.save(existingStaff);
        // Lưu sản phẩm đã cập nhật vào cơ sở dữ liệu
    }

    @Override
    public void deleteStaff(Long id) {
        UserEntity existingStaff = userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Không tìm thấy sản phẩm"));
        existingStaff.setDeleted(false);
        userRepository.save(existingStaff);
    }

    @Override
    public void restoreStaff(Long id) {
        UserEntity existingStaff = userRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Không tìm thấy sản phẩm"));
        existingStaff.setDeleted(true);
        userRepository.save(existingStaff);
    }
}
