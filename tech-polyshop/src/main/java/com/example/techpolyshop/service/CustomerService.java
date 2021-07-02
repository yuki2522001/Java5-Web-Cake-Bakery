package com.example.techpolyshop.service;

import java.util.List;
import java.util.Optional;

import com.example.techpolyshop.domain.Customer;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
	void updateResetToken(String token, String email) throws ClassNotFoundException;

    Customer findByResetToken(String resetToken);

    Optional<Customer> findByEmail(String email);

    // public Customer login(String email, String password);
	void updatePassword(Customer customer, String password);

    Customer getByResetToken(String token);
    
	Customer getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Customer> entities);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	void delete(Customer entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	long count();

	void deleteAllInBatch(Iterable<Customer> entities);

	<S extends Customer> boolean exists(Example<S> example);

	<S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Long id);

	<S extends Customer> S saveAndFlush(S entity);

	void flush();

	<S extends Customer> List<S> saveAll(Iterable<S> entities);

	Optional<Customer> findById(Long id);

	List<Customer> findAllById(Iterable<Long> ids);

	List<Customer> findAll(Sort sort);

	List<Customer> findAll();

	Page<Customer> findAll(Pageable pageable);

	<S extends Customer> S save(S entity);

	List<Customer> findByNameContaining(String name);
	
	Page<Customer> findByNameContaining(String name, Pageable pageable);
	
}
