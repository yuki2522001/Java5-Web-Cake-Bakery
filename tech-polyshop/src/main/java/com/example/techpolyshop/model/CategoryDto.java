package com.example.techpolyshop.model;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long categoryId;
    @NotEmpty
    @Length(min = 5)
    private String name;
    private Boolean isEdit = false;
}
