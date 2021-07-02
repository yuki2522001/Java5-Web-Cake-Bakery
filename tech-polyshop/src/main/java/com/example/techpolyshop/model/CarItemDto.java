package com.example.techpolyshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarItemDto {
    private Long productId;
    private String name;
    private int quantity;
    private double unitPrice;
    private double discount;
    private short status;
    private String image;
}
