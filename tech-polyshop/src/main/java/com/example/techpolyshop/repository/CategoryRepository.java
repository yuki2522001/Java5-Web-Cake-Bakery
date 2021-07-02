package com.example.techpolyshop.repository;

import java.util.List;

import com.example.techpolyshop.domain.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    // Tim kiem thong tin theo ten cua cac truong
    // name chua gia tri cua tham so truyen vao
    List<Category> findByNameContaining(String name);
    // tim kiem va phan trang
    Page<Category> findByNameContaining(String name, Pageable pageable);
}
