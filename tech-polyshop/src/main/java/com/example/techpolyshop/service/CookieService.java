// package com.example.techpolyshop.service;

// import javax.servlet.http.Cookie;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class CookieService {
// 	@Autowired
// 	HttpServletRequest request;
// 	@Autowired
// 	HttpServletResponse response;
	
// 	public Cookie get(String name) {
// 		Cookie[] cookies = request.getCookies();
// 		if(cookies !=null) {
// 			for(Cookie cookie : cookies) {
// 				if(cookie.getName().equalsIgnoreCase(name)) {
// 					return cookie;
// 				}
// 			}
// 		}
// 		return null;
// 	}
	
// 	public String getvalue(String name, String defaultValue) {
// 		Cookie cookie = get(name);
// 		if(cookie !=null) {
// 			return cookie.getValue();
// 		}
// 		return defaultValue;
// 	}
	
// 	public Cookie add(String name, String value, int hours) {
// 		Cookie cookie = new Cookie(name,value);
// 		cookie.setMaxAge(hours*60*60);
// 		cookie.setPath("/");
// 		response.addCookie(cookie);
// 		return cookie;
// 	}
	
// 	public void remove(String name) {
// 		add(name, "", 0);
// 	}
// }
