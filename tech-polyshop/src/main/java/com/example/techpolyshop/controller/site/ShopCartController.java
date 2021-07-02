package com.example.techpolyshop.controller.site;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.techpolyshop.domain.Customer;
import com.example.techpolyshop.domain.Order;
import com.example.techpolyshop.domain.OrderDetail;
import com.example.techpolyshop.domain.Product;
import com.example.techpolyshop.model.CarItemDto;
import com.example.techpolyshop.repository.OrderDetailRepository;
import com.example.techpolyshop.repository.OrderRepository;
import com.example.techpolyshop.service.CustomerService;
import com.example.techpolyshop.service.OrderService;
import com.example.techpolyshop.service.ProductService;
import com.example.techpolyshop.service.SessionService;
import com.example.techpolyshop.service.ShopCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("site/orders")
public class ShopCartController {
    @Autowired
    HttpSession session;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;
    @Autowired
    ShopCartService shopCartService;
    @Autowired
    CustomerService customerService;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    SessionService service;

    // @GetMapping("order")
    // public String list(Model model){
    //     model.addAttribute("CART_ITEMS", shopCartService.getAllItems());
    //     model.addAttribute("TOTAL", shopCartService.getAmount());
    //     // model.addAttribute("NoOfItems", shopCartService.getCount());
    //     return "site/orders/order";
    // }

    @GetMapping("order")
    public String list(Model model) {
        if (checkSecurity()) {
        model.addAttribute("CART_ITEMS", shopCartService.getAllItems());
        model.addAttribute("TOTAL", shopCartService.getAmount());
        // model.addAttribute("NoOfItems", shopCartService.getCount());
        return "site/orders/order";
        }
        return "redirect:/site/login";
    }
    public boolean checkSecurity() {
        String email = service.get("email");
        if (email != null) {
            return true;
        }
        return false;
    }

    @GetMapping("add/{productId}")
    public String addCart(@PathVariable("productId") Long productId){
        Product product = productService.findByProductId(productId);
        if (product != null){
            CarItemDto item = new CarItemDto();
            item.setProductId(productId);
            item.setName(product.getName());
            item.setUnitPrice(product.getUnitPrice());
            item.setQuantity(1);
            shopCartService.add(item);
        }
        return "redirect:/site/orders/order";
    }
    @GetMapping("clear")
    public String clearCart(){
        shopCartService.clear();
        return "redirect:/site/orders/order";
    }
    @GetMapping("delete/{productId}")
    public String removeCart(@PathVariable("productId") Long productId){
        shopCartService.remove(productId);
        return "redirect:/site/orders/order";
    }
    @PostMapping("update")
    public String update(@RequestParam("productId") Long productId,
    @RequestParam("quantity") Integer quantity){
        shopCartService.update(productId, quantity);
        return "redirect:/site/orders/order";
    }

    @RequestMapping("save")
    public String save(ModelMap model) {
        Order order = new Order();
        Optional<Customer> customer = customerService.findByEmail((String) session.getAttribute("email"));
        order.setOrderDate(new Date());
        order.setCustomer(customer.get());
        order.setAmount(shopCartService.getAmount());
        order.setStatus((short) 0);

        Collection<CarItemDto> list = shopCartService.getCartItems();
        List<OrderDetail> details = new ArrayList<>();
        for (CarItemDto product : list) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            Product product1 = new Product();
            product1.setProductId(product.getProductId());
            detail.setProduct(product1);

            detail.setQuantity(product.getQuantity());
            detail.setUnitPrice(product.getUnitPrice());
            details.add(detail);
        }
        orderService.create(order, details);
        // shopCartService.clear();
        model.addAttribute("message", "Order thành công!");
        return "site/orders/order";
    }

}

