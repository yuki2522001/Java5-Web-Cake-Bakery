package com.example.techpolyshop.repository;

import java.util.List;
import java.util.Optional;

import com.example.techpolyshop.domain.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    // Tim kiem thong tin theo ten cua cac truong
    // name chua gia tri cua tham so truyen vao
    List<Customer> findByNameContaining(String name);
    // tim kiem va phan trang
    Page<Customer> findByNameContaining(String name, Pageable pageable);

    Optional<Customer> findByEmail(String email);

    Customer findByResetToken(String resetToken);

    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    public Customer findbyemail(String email);

    @Query(value = "select COUNT(*) from customers", nativeQuery = true)
    Object countCustomer();

}
