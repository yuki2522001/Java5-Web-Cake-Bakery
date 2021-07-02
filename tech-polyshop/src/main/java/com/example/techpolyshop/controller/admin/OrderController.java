package com.example.techpolyshop.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import com.example.techpolyshop.domain.Order;
import com.example.techpolyshop.domain.OrderDetail;
import com.example.techpolyshop.model.OrderDto;
import com.example.techpolyshop.repository.OrderDetailRepository;
import com.example.techpolyshop.repository.OrderRepository;
import com.example.techpolyshop.service.OrderService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("admin/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository dao;

    @Autowired
    OrderDetailRepository odDao;

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("order", new OrderDto());
        return "admin/orders/addOrEdit";
    }

    @GetMapping("edit/{orderId}")
    public ModelAndView edit(ModelMap model, @PathVariable("orderId") Long orderId) {

        Order item = dao.findById(orderId).get();
        // model.addAttribute("item", item);
        model.addAttribute("order", item);

        List<OrderDetail> list = odDao.findByOrder(item);
        model.addAttribute("detail", list);

        model.addAttribute("message", "Customer is not existed !");
        return new ModelAndView("admin/orders/order_detail", model);
        // return new ModelAndView("redirect:/admin/orders", model);

    }

    @GetMapping("delete/{orderId}")
    public ModelAndView delete(ModelMap model, @PathVariable("orderId") Long orderId) {
        orderService.deleteById(orderId);
        model.addAttribute("message", "Order is deleted!");
        return new ModelAndView("forward:/admin/orders/search", model);
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("order") OrderDto dto,
            BindingResult result) {
        Order entity = new Order();
        BeanUtils.copyProperties(dto, entity);
        orderService.save(entity);
        model.addAttribute("message", "Customer is saved!");
        return new ModelAndView("forward:/admin/orders", model);
    }

    @RequestMapping("")
    public String list(ModelMap model) {
        List<Order> list = orderService.findAll();
        model.addAttribute("orders", list);
        return "redirect:searchpaginated";
    }

    @GetMapping("searchpaginated")
    public String search(ModelMap model, @RequestParam(name = "orderId", required = false) Long orderId,
            @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);// trang hien tai
        int pageSize = size.orElse(5);// kich thuoc cua page
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("orderId"));
        Page<Order> resultPage = null;

        if (orderId != null) {
            resultPage = orderService.findByOrderIdContaining(orderId, pageable);
            model.addAttribute("name", orderId);
        } else {
            resultPage = orderService.findAll(pageable);
        }
        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);
            if (totalPages > 5) {
                if (end == totalPages)
                    start = end - 5;
                else if (start == 1)
                    end = start + 5;
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("orderPage", resultPage);
        return "admin/orders/order_list";
    }
}