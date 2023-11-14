package com.example.admin.controller;

import com.example.admin.domain.Product;
import com.example.admin.domain.User;
import com.example.admin.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/staff")
    public String listUser(Model model)
    {
        model.addAttribute("staffs",staffService.getAllUsers("ROLE_STAFF"));
        return "AccountStaff";
    }

    @GetMapping("/staff/create")
    public String showCreateStaff(Model model){
        model.addAttribute("user", new User());
        return "CreateStaff";
    }
    @PostMapping("/staff/create")
    public String createStaff (@ModelAttribute User user){
        staffService.createStaff(user);
        return "redirect:/admin/staff";
    }
    @GetMapping("/staff/edit/{id}")
    public String showUpdateStaff(@PathVariable Long id, Model model) {
        model.addAttribute("user", staffService.getStaffById(id));
        return "EditStaff"; // Chuyển hướng về trang danh sách sản phẩm sau khi cập nhật
    }
    @PostMapping("/staff/edit")
    public String updateStaff(@ModelAttribute User user){
      staffService.updateStaff(user);
        return "redirect:/admin/staff";
    }
    @GetMapping("/staff/delete/{id}")
    public String deleteStaff(@PathVariable Long id,  Model model) {
        staffService.deleteStaff(id);

        return "redirect:/admin/staff"; // Chuyển hướng về trang danh sách sản phẩm sau khi cập nhật
    }
    @GetMapping("/staff/restore/{id}")
    public String restoreStaff(@PathVariable Long id,  Model model) {
       staffService.restoreStaff(id);
        return "redirect:/admin/staff"; // Chuyển hướng về trang danh sách sản phẩm sau khi cập nhật
    }
    @GetMapping("staff/restore")
    public String showRestoreStaff(Model model){
        model.addAttribute("staff",staffService.getAllUsers("ROLE_STAFF"));
        return "RestoreStaff";
    }
}
