package com.example.techpolyshop.repository;

import com.example.techpolyshop.domain.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginSiteReponsitory extends JpaRepository<Customer, String>{
    Customer findByEmail(String email);
}
