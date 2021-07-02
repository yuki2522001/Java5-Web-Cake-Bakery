package com.example.techpolyshop.service;

import java.util.Collection;

import com.example.techpolyshop.model.CarItemDto;

public interface ShopCartService {
    double getAmount();
    int getCount();
    Collection<CarItemDto> getAllItems();
    void clear();
    CarItemDto update(Long productId, int qty);
    Collection<CarItemDto> getCartItems();
    void remove(Long productId);
    void add(CarItemDto item);
}
