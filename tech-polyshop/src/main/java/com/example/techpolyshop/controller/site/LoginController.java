package com.example.techpolyshop.controller.site;
import com.example.techpolyshop.domain.Customer;
import com.example.techpolyshop.repository.SiteLoginRepository;
// import com.example.techpolyshop.service.CustomerService;
import com.example.techpolyshop.service.LoginSiteService;
import com.example.techpolyshop.service.ParamService;
import com.example.techpolyshop.service.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("site")
public class LoginController {
    @Autowired
	SiteLoginRepository dao;
	@Autowired
	ParamService paramService;
	@Autowired
	SessionService session;
	@Autowired
	LoginSiteService loginSiteService;

	@GetMapping("login")
	public String login() {
		return "site/account/login";
	}

	@PostMapping("login")
	public String login(Model model) {
		String email = paramService.getString("email", "");
		String pw = paramService.getString("password", "");
		try {
			Customer cus = loginSiteService.findByEmail(email);
			if (!cus.getPassword().equals(pw)) {
				model.addAttribute("message", "Invalid password");
			} else {
				String uri = session.get("security-uri");
				if (uri != null) {
					return "redirect:" + uri;
				} else {
					model.addAttribute("message", "Login successfully");
					session.set("email", email);
				}
			}
		} catch (Exception e) {
			model.addAttribute("message", "Invalid username");
		}
		return "redirect:/site/product/menu";
	}

	@GetMapping("logout")
	public String logout() {
		session.remove("email");
		return "site/account/login";
	}

		// @PostMapping("login")
	// public String login(Model model,
	// 		@RequestParam("email") String email,
	// 		@RequestParam("password") String password) {
	// 	try {
	// 		Customer user = dao.findByEmail(email);
	// 		if (!user.getPassword().equals(password)) {
	// 			model.addAttribute("message", "Invalid password");
	// 		} else {
	// 			String uri = session.get("security-uri");
	// 			if (uri != null) {
	// 				return "redirect:" + uri;
	// 			} else {
	// 				model.addAttribute("message", "Login succeed");
	// 				return "redirect:product/index";
	// 			}
	// 		}
	// 	} catch (Exception e) {
	// 		model.addAttribute("message", "Invalid password");
	// 	}
	// 	return "site/account/login";
	// }
}
