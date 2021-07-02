package com.example.techpolyshop.Impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.example.techpolyshop.model.CarItemDto;
import com.example.techpolyshop.service.ShopCartService;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShopCartService{
    Map<Long, CarItemDto> maps = new HashMap<>();
   
    @Override
    public void add(CarItemDto item){
        CarItemDto carItemDto = maps.get(item.getProductId());
        if (carItemDto == null){
            maps.put(item.getProductId(), item);
        }else{
            carItemDto.setQuantity(carItemDto.getQuantity() + 1);
        }
    }
    @Override
    public Collection<CarItemDto> getCartItems() {
        return maps.values();
    }
    @Override
    public void remove(Long productId){
        maps.remove(productId);
    }

    @Override
    public CarItemDto update(Long productId, int qty){
        CarItemDto carItemDto = maps.get(productId);
        carItemDto.setQuantity(qty);
        return carItemDto;
    }

    @Override
    public void clear(){
        maps.clear();
    }

    @Override
    public Collection<CarItemDto> getAllItems(){
        return maps.values();
    }

    @Override
    public int getCount(){
        return maps.values().size();
    }

    @Override
    public double getAmount(){
        return maps.values().stream()
                .mapToDouble(item -> item.getQuantity() * item.getUnitPrice())
                .sum();
    }
}
