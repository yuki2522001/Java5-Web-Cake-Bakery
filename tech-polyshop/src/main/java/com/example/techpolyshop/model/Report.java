package com.example.techpolyshop.model;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report implements Serializable{
    @Id
	Serializable group;
	Double sum;
	Long count;  
}
