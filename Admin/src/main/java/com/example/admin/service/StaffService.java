package com.example.admin.service;

import com.example.admin.domain.OrderHistory;
import com.example.admin.domain.Product;
import com.example.admin.domain.User;

import java.util.List;

public interface StaffService {
    List<User> getAllUsers(String staff);
    void createStaff(User user);
    User getStaffById(Long id);
    void updateStaff( User user);
    void deleteStaff(Long id);
    void restoreStaff(Long id);

    User getUserByUserName(String username);

    List<OrderHistory> getAllOrderByStaffIdAndByBetween(Long id);

    Long getTotalAmountToday(List<OrderHistory> orderHistories);
}


