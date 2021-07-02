package com.example.techpolyshop.repository;

import com.example.techpolyshop.domain.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteLoginRepository extends JpaRepository<Customer, Long>{
    Customer findByEmail(String email);
}
