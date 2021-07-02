package com.example.techpolyshop.service;

import com.example.techpolyshop.domain.Customer;

public interface LoginSiteService {
    Customer findByEmail(String email);
}
