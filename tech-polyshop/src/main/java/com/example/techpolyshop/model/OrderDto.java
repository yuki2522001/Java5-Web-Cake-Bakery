package com.example.techpolyshop.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private Date orderDate;
    private double amount;
    private short status;
    private Long categoryId;
    private Boolean isEdit = false;
}
