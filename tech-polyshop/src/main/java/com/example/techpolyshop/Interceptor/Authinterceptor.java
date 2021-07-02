package com.example.techpolyshop.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// import com.example.techpolyshop.domain.Account;
import com.example.techpolyshop.model.AccountDto;
import com.example.techpolyshop.service.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

@Service
public class Authinterceptor implements HandlerInterceptor {
	@Autowired
	SessionService session;
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uri = request.getRequestURI();
		AccountDto user = session.get("user");
		
		String error = "";
		if(user==null) {
			error ="please login!";
		}else if(!user.isAdmin()&& uri.startsWith("/admin/")) {
			error = "Access denied!";
		}
		if(error.length()>0) {
			session.set("security-uri",uri);
			response.sendRedirect("/admin/account/login?error="+error);
			return false;
		}
		return true;
		
	}
}