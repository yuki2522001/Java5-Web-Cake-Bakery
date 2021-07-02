package com.example.techpolyshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private Long orderDetailId;
    private Long productId;
    private int quantity;
    private double unitPrice;
   
}
