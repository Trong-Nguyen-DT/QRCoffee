package com.example.admin.controller;


import com.example.admin.domain.Product;
import com.example.admin.domain.User;
import com.example.admin.remote.RemoteService;
import com.example.admin.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class testAPI {

    @Autowired
    private StaffService staffService;

    @Autowired
    private RemoteService remoteService;


    @GetMapping("remote")
    public List<Product> getProductFromRemote() {
        return remoteService.getProductFromOtherClient();
    }


}


