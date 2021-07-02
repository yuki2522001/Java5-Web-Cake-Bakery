package com.example.techpolyshop.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
// Luu thong tin khach hang
public class Customer implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Column(columnDefinition = "nvarchar(50) not null")
    private String name;
    @Column(columnDefinition = "nvarchar(100) not null")
    private String email;
    @Column(length = 20, nullable = false)
    private String password;
    @Column(length = 20)
    private String phone;
    @Temporal(TemporalType.DATE)
    private Date registerData;
    @Column(nullable = false)
    private short status;
    private boolean role;
    private String address;
    private Date birthday;
    @Column(name = "reset_token")
    private String resetToken;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Order> orders;

    
}
