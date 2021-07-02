package com.example.techpolyshop.service;

import java.util.Collection;

import com.example.techpolyshop.model.OrderDetailDto;

public interface CartService {
    Long getCount();

	double getAmount();

	void update(Long productId, int quantity);

	void clear();

	Collection<OrderDetailDto> getOrderDetailDtos();

	void remove(Long productId);

	void add(OrderDetailDto item);
}
