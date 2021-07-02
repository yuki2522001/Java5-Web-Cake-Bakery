package com.example.techpolyshop.repository;

import java.util.List;

import com.example.techpolyshop.domain.Product;
import com.example.techpolyshop.model.Report;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    // Tim kiem thong tin theo ten cua cac truong
    // name chua gia tri cua tham so truyen vao
    List<Product> findByNameContaining(String name);
    // tim kiem va phan trang
    Page<Product> findByNameContaining(String name, Pageable pageable);

    Product findByProductId(Long productId);

    @Query(value = "SELECT new Report(o.category,sum(o.unitPrice),count(o))"
     + " FROM Product o"
     + " GROUP BY o.category" + " ORDER BY sum(o.unitPrice) DESC")
	List<Report> getInventoryByCategory();

}

