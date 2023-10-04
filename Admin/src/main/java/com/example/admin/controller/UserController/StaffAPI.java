package com.example.admin.controller.UserController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("table-api")
public class StaffAPI {

    @GetMapping()
    public String resetPageStaffHome(){
        return "redirect:/staff";
    }
}
