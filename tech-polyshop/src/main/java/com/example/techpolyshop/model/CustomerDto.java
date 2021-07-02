package com.example.techpolyshop.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long customerId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Date registerData;
    private short status;
    private boolean role;
    private String address;
    private Date birthday;
    private Boolean isEdit = false;
    // @Column
    private boolean isAdmin=true;
}
