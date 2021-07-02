package com.example.techpolyshop.model;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long productId;
    private String name;
    private int quantity;
    private String image;
    private MultipartFile imageFile;
    private double unitPrice;
    private String description;
    private double discount;
    private Date enteredDate;
    private short status; 
    // private Long category;
    private Long categoryId;
    private Boolean isEdit = false;

}
