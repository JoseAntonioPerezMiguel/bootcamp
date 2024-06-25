package com.example.domains.entities.models;

import java.io.Serializable;

import com.example.domains.entities.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class LanguageDTO implements Serializable {
	@JsonProperty("id")
	private int categoryId;
	
	@JsonProperty("name")
	private String name;
	
	public static LanguageDTO from(Category category) {
		return new LanguageDTO(category.getCategoryId(), category.getName());
	}
	
	public static Category from(LanguageDTO categoryDTO) {
		return new Category(categoryDTO.getCategoryId(), categoryDTO.getName());
	}
}
