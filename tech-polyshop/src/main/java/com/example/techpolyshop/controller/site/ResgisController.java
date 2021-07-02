package com.example.techpolyshop.controller.site;

import java.util.Optional;

import javax.validation.Valid;

import com.example.techpolyshop.domain.Customer;
import com.example.techpolyshop.model.CustomerDto;
import com.example.techpolyshop.service.CustomerService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("site/account")
public class ResgisController {
    @Autowired
    CustomerService customerService;

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("customer", new CustomerDto());
        return "site/account/resgis";
    }
    @GetMapping("profile")
    public String pro(Model model) {
        model.addAttribute("customer", new CustomerDto());
        return "site/account/profile";
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
           
            return new ModelAndView("site/account/profile", model);
        }
        model.addAttribute("message", "Register is not existed");
        return new ModelAndView("forward:/site/account", model) ;
    }

    @PostMapping("saveOrUpdate")
    public String saveOrUpdate(ModelMap model, 
    @Valid @ModelAttribute("category") CustomerDto dto, BindingResult result) {

        if(result.hasErrors()){
            return "site/account/profile";
            
        }
        Customer entity = new Customer();
        BeanUtils.copyProperties(dto, entity);
        customerService.save(entity);
        model.addAttribute("message", "Register Success!");
        return "redirect:/site/login";
       
     }
}
