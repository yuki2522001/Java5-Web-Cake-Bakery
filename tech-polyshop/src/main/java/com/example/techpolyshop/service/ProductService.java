package com.example.techpolyshop.service;

import java.util.List;
import java.util.Optional;

import com.example.techpolyshop.domain.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface ProductService {
	public Product findByProductId(Long productId);

    Product getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends Product> entities);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	void delete(Product entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	long count();

	void deleteAllInBatch(Iterable<Product> entities);

	<S extends Product> boolean exists(Example<S> example);

	<S extends Product> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Long id);

	<S extends Product> S saveAndFlush(S entity);

	void flush();

	<S extends Product> List<S> saveAll(Iterable<S> entities);

	Optional<Product> findById(Long productId);

	List<Product> findAllById(Iterable<Long> ids);

	List<Product> findAll(Sort sort);

	List<Product> findAll();

	Page<Product> findAll(Pageable pageable);

	<S extends Product> S save(S entity);

	List<Product> findByNameContaining(String name);
	
	Page<Product> findByNameContaining(String name, Pageable pageable);
	
}
