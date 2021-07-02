package com.example.techpolyshop.service;

import java.util.List;

import com.example.techpolyshop.domain.Product;

public interface OrderProductService {
    void update(Product pro);

	List<Product> findAll();

	void remove(int productId);

	void add(Product pro);

	Product findById(int productId);
}
