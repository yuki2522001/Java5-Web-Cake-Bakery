package com.example.techpolyshop.repository;

import java.util.List;

import com.example.techpolyshop.domain.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByOrderIdContaining(Long orderId, Pageable pageable);
    @Query(value = "select sum(o.amount), MONTH(o.orderDate) from Order o group by MONTH(o.orderDate)")
    List<Object[]> AmountList();
    // public void create(Order order);

    // void save(OrderDetail detail);
}
