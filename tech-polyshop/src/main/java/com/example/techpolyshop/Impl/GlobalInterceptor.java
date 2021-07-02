package com.example.techpolyshop.Impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.techpolyshop.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Service
public class GlobalInterceptor implements HandlerInterceptor {
	@Autowired
	CategoryRepository dao ;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response,Object handler ) throws Exception {
		request.setAttribute("uri", request.getRequestURI());
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, 
		HttpServletResponse response,
		Object handler,ModelAndView mv) throws Exception {
		request.setAttribute("categories",dao.findAll());
	}
}