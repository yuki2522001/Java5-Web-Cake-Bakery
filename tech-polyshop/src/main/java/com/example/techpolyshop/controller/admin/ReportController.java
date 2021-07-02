package com.example.techpolyshop.controller.admin;

import java.util.List;

import com.example.techpolyshop.model.Report;
import com.example.techpolyshop.repository.CustomerRepository;
import com.example.techpolyshop.repository.OrderRepository;
import com.example.techpolyshop.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class ReportController {
    @Autowired
	ProductRepository dao;
	@Autowired
	CustomerRepository DAO;
	@Autowired
	OrderRepository OD;
	
	// @RequestMapping("admin/report/rep")
	@GetMapping("report")
	public String inventory(Model model) {
	List<Report> items = dao.getInventoryByCategory();
	model.addAttribute("items", items);
	model.addAttribute("cus", DAO.countCustomer());
	model.addAttribute("value", OD.AmountList());
	return "admin/report/report";
	}
}


