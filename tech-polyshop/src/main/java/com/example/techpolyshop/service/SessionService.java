package com.example.techpolyshop.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
	@Autowired
	HttpSession session;
	
	@SuppressWarnings("unchecked")
	public <T> T get(String name) {
		return (T) session.getAttribute(name);
	}
	
	public <T> T get(String name, T defauValue) {
		T value = get(name);
		return value != null ? value : defauValue;
	}
	
	public void set(String name, Object value) {
		session.setAttribute(name, value);
	}
	
	public void remove(String name) {
		session.removeAttribute(name);
	}

}
