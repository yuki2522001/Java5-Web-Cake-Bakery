// package com.example.techpolyshop.controller.site;

// import java.util.List;

// import com.example.techpolyshop.domain.Product;
// import com.example.techpolyshop.model.CustomerDto;
// import com.example.techpolyshop.model.OrderDetailDto;
// import com.example.techpolyshop.model.OrderDto;
// import com.example.techpolyshop.model.ProductDto;
// import com.example.techpolyshop.repository.OrderDetailRepository;
// import com.example.techpolyshop.repository.OrderRepository;
// import com.example.techpolyshop.service.CartService;
// import com.example.techpolyshop.service.CustomerService;
// import com.example.techpolyshop.service.ProductService;
// import com.example.techpolyshop.service.SessionService;

// import org.springframework.beans.BeanUtils;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;

// @Controller
// @RequestMapping("site/orders")
// public class Order {
//     @Autowired
//     OrderRepository dao;
//     @Autowired
//     OrderDetailRepository odDao;
// 	@Autowired
// 	CustomerService customerService;
//     @Autowired
// 	ProductService productService;
// 	@Autowired
// 	CartService shoppingCartService;
// 	@Autowired
//     SessionService service;
    
// 	@GetMapping("ShopCart")
//     public String list(Model model) {
//         if (checkSecurity()) {
//             model.addAttribute("order", customerService.findAll());
//             return "site/orders/ShopCart";
//         }
//         return "redirect:/site/login";
//     }
//     public boolean checkSecurity() {
//         String email = service.get("EMAIL");
//         if (email != null) {
//             return true;
//         }
//         return false;
//     }
// 	@ModelAttribute("customer")
//     public List<CustomerDto> getCategories(){
//         return customerService.findAll().stream().map(item->{
//             CustomerDto dto = new CustomerDto();
//             BeanUtils.copyProperties(item, dto);
//             return dto;
//         }).toList();
//     }
// 	@ModelAttribute("product")
//     public List<ProductDto> getProductDtos(){
//         return productService.findAll().stream().map(item->{
//             ProductDto dto = new ProductDto();
//             BeanUtils.copyProperties(item, dto);
//             return dto;
//         }).toList();
//     }
// 	@GetMapping("add")
//     public String add(Model model) {
//         model.addAttribute("order", new OrderDto());
//         return "admin/orders/addOrEdit";
//     }
// }
