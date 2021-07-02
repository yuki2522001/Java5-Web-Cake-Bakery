package com.example.techpolyshop.Impl;

import com.example.techpolyshop.domain.Product;
import com.example.techpolyshop.service.OrderProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class OrderProductImpl implements OrderProductService{
    private static List<Product> list = new ArrayList<>();
	// Them 1 sp moi vao list
	@Override
	public void add(Product pro) {
		list.add(pro);
	}
	// Xoa 1 sp theo Id
	@Override
	public void remove(int productId) {
		list.removeIf(item->item.getProductId() == productId);
	}
	// Tra ve list cac pro hien co
	@Override
	public List<Product> findAll() {
		return list;
	}
	@Override
	public Product findById(int productId) {
		Optional<Product> opt = list.stream().filter(item->item.getProductId() == productId).findFirst();
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	@Override
	public void update(Product pro) {
		for(int i = 0; i < list.size(); i ++) {
			Product item = list.get(i);
			if (item.getProductId() == pro.getProductId()) {
				list.set(i, pro);
				break;
			}
		}
	}
}
