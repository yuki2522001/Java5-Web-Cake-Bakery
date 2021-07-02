package com.example.techpolyshop.Impl;

import java.util.List;
import java.util.Optional;

import com.example.techpolyshop.domain.Order;
import com.example.techpolyshop.domain.OrderDetail;
import com.example.techpolyshop.repository.OrderDetailRepository;
import com.example.techpolyshop.repository.OrderRepository;
import com.example.techpolyshop.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    public void create(Order order, List<OrderDetail> details) {
        orderRepository.save(order);
        for (OrderDetail detail : details) {
            orderDetailRepository.save(detail);
        }
    }

    public long count() {
        return orderRepository.count();
    }

    public <S extends Order> long count(Example<S> arg0) {
        return orderRepository.count(arg0);
    }

    public void delete(Order arg0) {
        orderRepository.delete(arg0);
    }

    public void deleteAll() {
        orderRepository.deleteAll();
    }

    public void deleteAll(Iterable<? extends Order> arg0) {
        orderRepository.deleteAll(arg0);
    }

    public void deleteAllById(Iterable<? extends Long> arg0) {
        orderRepository.deleteAllById(arg0);
    }

    public void deleteAllByIdInBatch(Iterable<Long> arg0) {
        orderRepository.deleteAllByIdInBatch(arg0);
    }

    public void deleteAllInBatch() {
        orderRepository.deleteAllInBatch();
    }

    public void deleteAllInBatch(Iterable<Order> arg0) {
        orderRepository.deleteAllInBatch(arg0);
    }

    public void deleteById(Long arg0) {
        orderRepository.deleteById(arg0);
    }

    public boolean existsById(Long arg0) {
        return orderRepository.existsById(arg0);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAll(Sort arg0) {
        return orderRepository.findAll(arg0);
    }

    public <S extends Order> List<S> findAll(Example<S> arg0) {
        return orderRepository.findAll(arg0);
    }

    public <S extends Order> List<S> findAll(Example<S> arg0, Sort arg1) {
        return orderRepository.findAll(arg0, arg1);
    }

    public Page<Order> findAll(Pageable arg0) {
        return orderRepository.findAll(arg0);
    }

    public <S extends Order> Page<S> findAll(Example<S> arg0, Pageable arg1) {
        return orderRepository.findAll(arg0, arg1);
    }

    public List<Order> findAllById(Iterable<Long> arg0) {
        return orderRepository.findAllById(arg0);
    }

    public Optional<Order> findById(Long arg0) {
        return orderRepository.findById(arg0);
    }

    public Page<Order> findByOrderIdContaining(Long orderId, Pageable pageable) {
        return orderRepository.findByOrderIdContaining(orderId, pageable);
    }

    public <S extends Order> Optional<S> findOne(Example<S> arg0) {
        return orderRepository.findOne(arg0);
    }

    public void flush() {
        orderRepository.flush();
    }

    public Order getById(Long arg0) {
        return orderRepository.getById(arg0);
    }

    public <S extends Order> S save(S arg0) {
        return orderRepository.save(arg0);
    }

    public <S extends Order> List<S> saveAll(Iterable<S> arg0) {
        return orderRepository.saveAll(arg0);
    }

    public <S extends Order> List<S> saveAllAndFlush(Iterable<S> arg0) {
        return orderRepository.saveAllAndFlush(arg0);
    }

    public <S extends Order> S saveAndFlush(S arg0) {
        return orderRepository.saveAndFlush(arg0);
    }
 
}
