package com.example.techpolyshop.Impl;

import com.example.techpolyshop.domain.Customer;
import com.example.techpolyshop.repository.LoginSiteReponsitory;
import com.example.techpolyshop.service.LoginSiteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginSiteImpl implements LoginSiteService{
    @Autowired
    private LoginSiteReponsitory loginSiteReponsitory;

    public Customer findByEmail(String email) {
        return loginSiteReponsitory.findByEmail(email);
    }

}
