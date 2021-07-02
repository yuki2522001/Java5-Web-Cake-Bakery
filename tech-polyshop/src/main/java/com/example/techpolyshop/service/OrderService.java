package com.example.techpolyshop.service;
import java.util.List;
import java.util.Optional;

import com.example.techpolyshop.domain.Order;
import com.example.techpolyshop.domain.OrderDetail;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;


@Service
@SessionScope
public interface OrderService {
    long count();

    <S extends Order> long count(Example<S> arg0);

    void delete(Order arg0);

    void deleteAll();

    void deleteAll(Iterable<? extends Order> arg0);

    void deleteAllById(Iterable<? extends Long> arg0);

    void deleteAllByIdInBatch(Iterable<Long> arg0);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<Order> arg0);

    void deleteById(Long arg0);

    boolean existsById(Long arg0);

    List<Order> findAll();

    List<Order> findAll(Sort arg0);

    <S extends Order> List<S> findAll(Example<S> arg0);

    <S extends Order> List<S> findAll(Example<S> arg0, Sort arg1);

    Page<Order> findAll(Pageable arg0);

    <S extends Order> Page<S> findAll(Example<S> arg0, Pageable arg1);

    List<Order> findAllById(Iterable<Long> arg0);

    Optional<Order> findById(Long arg0);

    Page<Order> findByOrderIdContaining(Long orderId, Pageable pageable);

    <S extends Order> Optional<S> findOne(Example<S> arg0);

    void flush();

    Order getById(Long arg0);

    <S extends Order> S save(S arg0);

    <S extends Order> List<S> saveAll(Iterable<S> arg0);

    <S extends Order> List<S> saveAllAndFlush(Iterable<S> arg0);

    <S extends Order> S saveAndFlush(S arg0);

    void create(Order order, List<OrderDetail> details);  
}
