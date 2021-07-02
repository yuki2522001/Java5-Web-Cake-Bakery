package com.example.techpolyshop.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import com.example.techpolyshop.domain.Account;
import com.example.techpolyshop.model.AccountDto;
import com.example.techpolyshop.service.AccountService;
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
@RequestMapping("admin/account")
public class AccountCRUD {
    @Autowired
    AccountService accountService;
    @Autowired
    SessionService service;

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("acc", new AccountDto());
        return "admin/account/addOrEdit";
    }

    @GetMapping("edit/{username}")
    public ModelAndView edit(ModelMap model, @PathVariable("username") String username) {
        Optional <Account> opt = accountService.findById(username);
        AccountDto dto = new AccountDto();
        if(opt.isPresent()){
            Account entity = opt.get();
            BeanUtils.copyProperties(entity, dto);
            dto.setIsEdit(true); // thiet lap o che do chinh sua thong tin
            model.addAttribute("acc", dto);
           
            return new ModelAndView("admin/account/addOrEdit", model);
        }
        model.addAttribute("message", "Account is not existed");
        return new ModelAndView("forward:/admin/account", model) ;
    }

    @GetMapping("delete/{username}")
    public ModelAndView delete(ModelMap model, @PathVariable("username") String username) {
        accountService.deleteById(username);
        model.addAttribute("message", "Account is deleted!");
        return new ModelAndView ("forward:/admin/account/searchpaginated", model);
    }

    @PostMapping("saveOrUpdate")
    public String saveOrUpdate(ModelMap model, 
    @Valid @ModelAttribute("acc") AccountDto dto, BindingResult result) {

        if(result.hasErrors()){
            return "admin/account/addOrEdit";
            // return new ModelAndView("admin/cccount/addOrEdit"); 
        }
        Account entity = new Account();
        BeanUtils.copyProperties(dto, entity);
        accountService.save(entity);
        model.addAttribute("message", "Account is saved!");
        return "forward:/admin/account";
        // return new ModelAndView("forward:/admin/account", model) ;
     }
    @RequestMapping("")
    public String list(ModelMap model) {
        List<Account> list = accountService.findAll();
        model.addAttribute("account", list);
        return "redirect:searchpaginated";
    }
    
    @GetMapping("searchpaginated")
    public String search(ModelMap model,
    @RequestParam(name = "username", required = false) String username,
    @RequestParam("page") Optional<Integer> page, 
    @RequestParam("size") Optional<Integer> size) {
       int currentPage = page.orElse(1); // gia tri ngam dinh la 1
       int pageSize = size.orElse(5); // ngam dinh 5 phan tu tren 1 trang

       Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("username"));
       Page<Account> resultPage = null;
        if(StringUtils.hasText(username)){ // neu ten ton tai thi goi 
           resultPage = accountService.findByUsernameContaining(username, pageable);
            model.addAttribute("name", username);
        }else {
            resultPage = accountService.findAll(pageable);
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
        model.addAttribute("accountPage", resultPage);
        return "admin/account/searchpaginated";
    }
}
