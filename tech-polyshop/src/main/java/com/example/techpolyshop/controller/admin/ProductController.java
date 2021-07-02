package com.example.techpolyshop.controller.admin;

import java.io.IOException;
// import java.io.IOException;
import java.util.List;
import java.util.Optional;
// import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import com.example.techpolyshop.domain.Category;
import com.example.techpolyshop.domain.Product;
import com.example.techpolyshop.model.CategoryDto;
// import com.example.techpolyshop.model.CategoryDto;
import com.example.techpolyshop.model.ProductDto;
import com.example.techpolyshop.repository.CategoryRepository;
import com.example.techpolyshop.repository.ProductRepository;
import com.example.techpolyshop.service.CategoryService;
import com.example.techpolyshop.service.ProductService;
import com.example.techpolyshop.service.SessionService;
import com.example.techpolyshop.service.StorageService;

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
import org.springframework.web.multipart.MultipartFile;
// import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/product")
public class ProductController {
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

    @GetMapping("contact")
    public String index(Model model) {
        model.addAttribute("product", new ProductDto());
        return "admin/product/contact";
    }
    
    @GetMapping("index")
    public String list(Model model) {
        if (checkSecurity()) {
            model.addAttribute("products", productService.findAll());
            return "admin/product/index"; 
        }
        return "redirect:/admin/login";
    }

    public boolean checkSecurity() {
        String username = service.get("USERNAME");
        if (username != null) {
            return true;
        }
        return false;
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("product", new ProductDto());
        return "admin/product/addOrEdit";
    }

    @GetMapping("edit/{productId}")
    public ModelAndView edit(ModelMap model, @PathVariable("productId") Long productId) {
      
        Optional<Product> opt = productService.findById(productId);
        ProductDto dto = new ProductDto();
        if (opt.isPresent()) {
            Product entity = opt.get();
            BeanUtils.copyProperties(entity, dto);
            dto.setCategoryId(entity.getCategory().getCategoryId());
            dto.setIsEdit(true); // thiet lap o che do chinh sua thong tin
            model.addAttribute("product", dto);

            return new ModelAndView("admin/product/addOrEdit", model);
        }
        model.addAttribute("message", "Product is not existed");
        return new ModelAndView("forward:/admin/product", model);
    }
    // @GetMapping("images/{filename:.+}")
    // @ResponseBody
    // public ResponseEntity<Resource> serveFile(@PathVariable String filename){
    //     Resource file = storageService.loadAsResource(filename);
    //     return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
    //     "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    // }

    @GetMapping("delete/{productId}")
    public ModelAndView delete(ModelMap model, @PathVariable("productId") Long productId) {
        productService.deleteById(productId);
        model.addAttribute("message", "product is deleted!");

        return new ModelAndView("forward:/admin/product/searchpaginated", model);
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView addCategory(@Valid ProductDto dto, BindingResult result,
            @RequestParam("file_product") MultipartFile file, Model model) throws IllegalStateException, IOException {    
        Product entity = new Product();
        BeanUtils.copyProperties(dto, entity);
            if (!file.isEmpty()) {
                entity.setImage("no-product.png");

            }
        Category category = new Category();
        category.setCategoryId(dto.getCategoryId());
        entity.setCategory(category);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        entity.setImage(fileName);

        Product savedProduct = proDao.save(entity);

        String uploadDir = "product-photos/" + savedProduct.getProductId();

        FileUploadUtil.saveFile(uploadDir, fileName, file);

        List<Product> list = productService.findAll();
        model.addAttribute("product", list);
        model.addAttribute("message", "Product is saved!");
        return new ModelAndView("forward:/admin/product") ;
        
    }
    // @PostMapping("saveOrUpdate")
    // public ModelAndView addCategory( ModelMap model,
    //     @Valid @ModelAttribute("product") ProductDto dto, BindingResult result) {

    //         if(result.hasErrors()){
    //             return new ModelAndView("admin/product/addOrEdit");
    //         }

    //     Product entity = new Product();
    //     BeanUtils.copyProperties(dto, entity);
    //     Category category = new Category();
    //     category.setCategoryId(dto.getCategoryId());
    //     entity.setCategory(category);

    //     if(!dto.getImageFile().isEmpty()){
    //         UUID uuid = UUID.randomUUID();
    //         String uuString = uuid.toString();
    //         entity.setImage(storageService.getStoredFilename(dto.getImageFile(), uuString));
    //         storageService.store(dto.getImageFile(), entity.getImage());
    //     }
    //     productService.save(entity);
    //     model.addAttribute("message", "Product is saved!");
    //     return new ModelAndView("forward:/admin/product", model) ;
        
    // }

    // @ModelAttribute("category")
    // public List<Category> getCategory() {
    //     return categoryService.findAll();
    // }

    @ModelAttribute("categories")
    public List<CategoryDto> getCategories(){
        return categoryService.findAll().stream().map(item->{
            CategoryDto dto = new CategoryDto();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).toList();
    }
    
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
        return "admin/product/search";
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
        return "admin/product/searchpaginated";
    }

    // @GetMapping("menu")
    // public String menu(ModelMap model, @RequestParam(name = "name", required = false) String name,
    // @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
    //     int currentPage = page.orElse(1); // gia tri ngam dinh la 1
    //     int pageSize = size.orElse(6); // ngam dinh 5 phan tu tren 1 trang

    //     Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
    //     Page<Product> resultPage = null;
    //     if (StringUtils.hasText(name)) { // neu ten ton tai thi goi
    //         resultPage = productService.findByNameContaining(name, pageable);
    //         model.addAttribute("name", name);
    //     } else {
    //         resultPage = productService.findAll(pageable);
    //     }
    //     // tra ve tong so trang da duoc phan trang
    //     int totalPages = resultPage.getTotalPages();
    //     if (totalPages > 0) {
    //         int start = Math.max(1, currentPage - 2);
    //         int end = Math.min(currentPage + 2, totalPages);

    //         if (totalPages > 6) {
    //             if (end == totalPages)
    //                 start = end - 6;
    //             else if (start == 1)
    //                 end = start + 6;
    //         }
    //         List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    //         model.addAttribute("pageNumbers", pageNumbers);
    //     }
    //     model.addAttribute("productPage", resultPage);
    //     return "admin/product/menu";
    // }

}
