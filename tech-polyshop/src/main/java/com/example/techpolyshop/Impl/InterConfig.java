package com.example.techpolyshop.Impl;

import com.example.techpolyshop.Interceptor.Authinterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration	
public class InterConfig implements WebMvcConfigurer {
	
	@Autowired
	GlobalInterceptor global;
	
	@Autowired
	Authinterceptor auth;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(auth)
		.addPathPatterns("/account/edit",
				"/account/chgpwd","/orders/**")
		.excludePathPatterns("/assets/","/admin/","admin/product/");
	}
}
