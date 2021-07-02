package com.example.techpolyshop.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.techpolyshop.model.CustomerDto;
import com.example.techpolyshop.service.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

@Service
public class AuthinterceptorSite implements HandlerInterceptor{
    @Autowired
	SessionService session;
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uri = request.getRequestURI();
		CustomerDto user = session.get("user");
		
		String error = "";
		if(user==null) {
			error ="please login!";
		}else if(!user.isAdmin()&& uri.startsWith("/site/")) {
			error = "Access denied!";
		}
		if(error.length()>0) {
			session.set("security-uri",uri);
			response.sendRedirect("/site/account/login?error="+error);
			return false;
		}
		return true;
		
	}
}
