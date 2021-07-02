package com.example.techpolyshop.controller.admin;

import com.example.techpolyshop.domain.Customer;
import com.example.techpolyshop.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ChangePasswordController {
    @Autowired
    CustomerService customerService;

    @PostMapping
    public void name(Customer user) {
        // if (user.getPassword() == null || user.getPassword().equals("")) {
        // // throw exception...
        // }
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        // customerService.save(user);
        // return "";
    }
}
