package com.example.techpolyshop.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto implements Serializable{
	@NotEmpty
	private	String username;
	@NotEmpty
	@Length(min = 6)
	private String password;
	private Boolean isEdit = false;
	private boolean isAdmin=true; 
}
