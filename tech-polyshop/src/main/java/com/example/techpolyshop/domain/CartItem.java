package com.example.techpolyshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// luu thong tin 1 sp khi them vao gio hang
public class CartItem {
    private int productId;
    private String name;
    private int quantity;
    private double unitPrice;
}
