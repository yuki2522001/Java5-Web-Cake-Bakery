package com.example.techpolyshop.repository;
import java.util.List;

import com.example.techpolyshop.domain.Order;
import com.example.techpolyshop.domain.OrderDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrder(Order order);
    // public void save(OrderDetail detail);
}
 