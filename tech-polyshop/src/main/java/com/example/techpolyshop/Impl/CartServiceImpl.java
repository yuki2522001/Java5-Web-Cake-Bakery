package com.example.techpolyshop.Impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.example.techpolyshop.model.OrderDetailDto;
import com.example.techpolyshop.service.CartService;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class CartServiceImpl implements CartService{
    private Map<Long, OrderDetailDto> map = new HashMap<Long, OrderDetailDto>();
	
	@Override
	public void add(OrderDetailDto item) {
		OrderDetailDto existedItem = map.get(item.getProductId());
		// ton tai trong gio hang
		if(existedItem != null) {
			existedItem.setQuantity(item.getQuantity() + existedItem.getQuantity());
		}else {		 
			map.put(item.getProductId(), item);
		}
	}
	@Override
	public void remove(Long productId) {
		map.remove(productId);
	}

	@Override
	public Collection<OrderDetailDto> getOrderDetailDtos(){
		return map.values();
	}
	@Override
	public void clear() {
		map.clear();
	}
	@Override
	public void update(Long productId, int quantity) {
		OrderDetailDto item = map.get(productId);
		item.setQuantity(quantity);
		if(item.getQuantity() <= 0) {
			map.remove(productId);
		}
	}
	@Override
	public double getAmount() {
		return map.values().stream().mapToDouble(item-> item.getQuantity() * item.getUnitPrice()).sum();
	}
	@Override
	public Long getCount() {
		if(map.isEmpty())
			return (long) 0;
		return (long) map.values().size();
	}
}
