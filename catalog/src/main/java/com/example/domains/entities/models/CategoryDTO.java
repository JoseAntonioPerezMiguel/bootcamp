package com.example.domains.entities.models;

import java.io.Serializable;

import com.example.domains.entities.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDTO implements Serializable {
	@JsonProperty("id")
	private int categoryId;
	
	@JsonProperty("name")
	private String name;
	
	public static CategoryDTO from(Category category) {
		return new CategoryDTO(category.getCategoryId(), category.getName());
	}
	
	public static Category from(CategoryDTO categoryDTO) {
		return new Category(categoryDTO.getCategoryId(), categoryDTO.getName());
	}
}
