package com.example.techpolyshop.controller.site;

// import java.io.IOException;
import java.util.List;
import java.util.Optional;
// import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.example.techpolyshop.domain.Product;
// import com.example.techpolyshop.model.CategoryDto;
import com.example.techpolyshop.model.ProductDto;
import com.example.techpolyshop.repository.CategoryRepository;
import com.example.techpolyshop.repository.ProductRepository;
import com.example.techpolyshop.service.CategoryService;
import com.example.techpolyshop.service.ProductService;
import com.example.techpolyshop.service.SessionService;
import com.example.techpolyshop.service.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("site/product")
public class Menuu {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository proDao;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository cateDao;
    @Autowired
    StorageService storageService;
    @Autowired
    SessionService service;

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("product", new ProductDto());
        return "site/product/index";
    }
    @GetMapping("abount")
    public String abount(Model model) {
        model.addAttribute("product", new ProductDto()); 
        return "site/product/about-us";
    }
    @GetMapping("blog")
    public String blog(Model model) {
        model.addAttribute("product", new ProductDto()); 
        return "site/product/blog-2column";
    }
    @GetMapping("contact")
    public String contac(Model model) {
        model.addAttribute("product", new ProductDto());
        return "admin/product/contact";
    }


    // @GetMapping("list")
    // public String list(Model model) {
    //     if (checkSecurity()) {
    //         model.addAttribute("product", productService.findAll());
    //         return "/admin/orders/searchpaginated"; 
    //     }
    //     return "redirect:/site/login";
    // }

    // public boolean checkSecurity() {
    //     String email = service.get("email");
    //     if (email != null) {
    //         return true;
    //     }
    //     return false;
    // }
    // @GetMapping("add")
    // public String add(Model model) {
    //     model.addAttribute("product", new ProductDto());
    //     return "site/product/addOrEdit";
    // }

    @RequestMapping("")
    public String list(ModelMap model) {
        List<Product> list = productService.findAll();
        model.addAttribute("products", list);
        return "redirect:searchpaginated";
    }

    @GetMapping("search")
    public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
        List<Product> list = null;
        if (StringUtils.hasText(name)) {
            list = productService.findByNameContaining(name);
        } else {
            list = productService.findAll();
        }
        model.addAttribute("products", list);
        return "site/product/search";
    }

    @GetMapping("searchpaginated")
    public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
            @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1); // gia tri ngam dinh la 1
        int pageSize = size.orElse(5); // ngam dinh 5 phan tu tren 1 trang

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
        Page<Product> resultPage = null;
        if (StringUtils.hasText(name)) { // neu ten ton tai thi goi
            resultPage = productService.findByNameContaining(name, pageable);
            model.addAttribute("name", name);
        } else {
            resultPage = productService.findAll(pageable);
        }
        // tra ve tong so trang da duoc phan trang
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
        model.addAttribute("productPage", resultPage);
        return "site/product/searchpaginated";
    }

    @GetMapping("menu")
    public String menu(ModelMap model, @RequestParam(name = "name", required = false) String name,
    @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1); // gia tri ngam dinh la 1
        int pageSize = size.orElse(6); // ngam dinh 5 phan tu tren 1 trang

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
        Page<Product> resultPage = null;
        if (StringUtils.hasText(name)) { // neu ten ton tai thi goi
            resultPage = productService.findByNameContaining(name, pageable);
            model.addAttribute("name", name);
        } else {
            resultPage = productService.findAll(pageable);
        }
        // tra ve tong so trang da duoc phan trang
        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);

            if (totalPages > 6) {
                if (end == totalPages)
                    start = end - 6;
                else if (start == 1)
                    end = start + 6;
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("productPage", resultPage);
        return "site/product/menu";
    }

}
