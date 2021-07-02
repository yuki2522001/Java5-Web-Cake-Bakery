package com.example.techpolyshop.controller.admin;

import javax.servlet.http.HttpSession;

import com.example.techpolyshop.domain.Account;
import com.example.techpolyshop.repository.AccountRepository;
import com.example.techpolyshop.service.AccountService;
import com.example.techpolyshop.service.ParamService;
import com.example.techpolyshop.service.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AccountController {
	@Autowired
	AccountRepository dao;
	@Autowired
	ParamService paramService;
	@Autowired
	SessionService session;
	@Autowired
	AccountService accountService;
	@Autowired
	HttpSession httpSession;

	@GetMapping("login")
	public String login() {
		return "admin/account/login";
	}

	@PostMapping("login")
	public String login(Model model) {
		String username = paramService.getString("username", "");
		String pw = paramService.getString("password", "");
		try {
			Account acc = accountService.findByUsername(username);
			if (!acc.getPassword().equals(pw)) {
				model.addAttribute("message", "Invalid password");
			} else {
				String uri = session.get("security-uri"); // lưu vào session
				if (uri != null) {
					return "redirect:" + uri;
				} else {
					model.addAttribute("message", "Login successfully");
					session.set("USERNAME", username);
				}
			}
		} catch (Exception e) {
			model.addAttribute("message", "Invalid username");
			
		}
		return "redirect:/admin/product/index";
	}

	@GetMapping("logout")
	public String logout() {
		session.remove("USERNAME");
		return "admin/account/login";
	}
}


// @GetMapping("alogin")
// public String login (Model model){
// model.addAttribute("account", new AdminLoginDto());
// return "admin/account/login";
// }
// @PostMapping("alogin")
// public ModelAndView login (ModelMap model,
// @Valid @ModelAttribute("account") AdminLoginDto dto, BindingResult result){
// if(result.hasErrors()){
// return new ModelAndView("/admin/account/login", model);
// }
// Account acc = accountService.login(dto.getUsername(), dto.getPassword());
// if(acc == null){
// model.addAttribute("message", "Invalid username or password");
// }
// session.setAttribute("username", acc.getUsername());
// return new ModelAndView("forward:/admin/product/index", model);
// }
