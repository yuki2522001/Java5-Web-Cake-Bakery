package com.example.techpolyshop.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.validation.Valid;

import com.example.techpolyshop.domain.Customer;
import com.example.techpolyshop.model.CustomerDto;
import com.example.techpolyshop.service.CustomerService;
import com.example.techpolyshop.service.SessionService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    SessionService service;

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("customer", new CustomerDto());
        return "admin/customer/addOrEdit";
    }
    @GetMapping("edit/{customerId}")
    public ModelAndView edit(ModelMap model, @PathVariable("customerId") Long customerId) {
        Optional <Customer> opt = customerService.findById(customerId);
        CustomerDto dto = new CustomerDto();
        if(opt.isPresent()){
            Customer entity = opt.get();
            BeanUtils.copyProperties(entity, dto);
            dto.setIsEdit(true); // thiet lap o che do chinh sua thong tin
            model.addAttribute("customer", dto);
           
            return new ModelAndView("admin/customer/addOrEdit", model);
        }
        model.addAttribute("message", "customer is not existed");
        return new ModelAndView("forward:/admin/customer", model) ;
    }

    @GetMapping("delete/{customerId}")
    public ModelAndView delete(ModelMap model, @PathVariable("customerId") Long customerId) {
        customerService.deleteById(customerId);
        model.addAttribute("message", "customer is deleted!");

        return new ModelAndView("forward:/admin/customer/searchpaginated", model) ;
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model, 
    @Valid @ModelAttribute("category") CustomerDto dto, BindingResult result) {

        if(result.hasErrors()){
            return new ModelAndView("admin/customer/addOrEdit"); 
        }
        Customer entity = new Customer();
        BeanUtils.copyProperties(dto, entity);
        customerService.save(entity);
        model.addAttribute("message", "Category is saved!");
        return new ModelAndView("forward:/admin/customer", model) ;
     }
    @RequestMapping("")
    public String list(ModelMap model) {
        List<Customer> list = customerService.findAll();
        model.addAttribute("customer", list);
        return "redirect:searchpaginated";
    }

    @GetMapping("search")
    public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
        List<Customer> list = null;
        if(StringUtils.hasText(name)){
           list = customerService.findByNameContaining(name);
        }else {
            list = customerService.findAll();
        }
        model.addAttribute("customers", list);
        return "admin/customer/search";
    }
    
    @GetMapping("searchpaginated")
    public String search(ModelMap model,
    @RequestParam(name = "name", required = false) String name,
    @RequestParam("page") Optional<Integer> page, 
    @RequestParam("size") Optional<Integer> size) {
       int currentPage = page.orElse(1); // gia tri ngam dinh la 1
       int pageSize = size.orElse(5); // ngam dinh 5 phan tu tren 1 trang

       Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
       Page<Customer> resultPage = null;
        if(StringUtils.hasText(name)){ // neu ten ton tai thi goi 
           resultPage = customerService.findByNameContaining(name, pageable);
            model.addAttribute("name", name);
        }else {
            resultPage = customerService.findAll(pageable);
        }
        // tra ve tong so trang da duoc phan trang
        int totalPages = resultPage.getTotalPages();
        if(totalPages > 0){
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);

            if(totalPages > 5){
                if(end == totalPages) start = end - 5;
                else if(start == 1) end = start + 5;
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
            .boxed()
            .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("customerPage", resultPage);
        return "admin/customer/searchpaginated";
    }

}
