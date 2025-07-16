package com.realestate.finder.mapper;

import org.springframework.stereotype.Component;

import com.realestate.finder.dto.request.CategoryRequestDTO;
import com.realestate.finder.dto.response.CategoryResponseDTO;
import com.realestate.finder.entity.Category;

@Component
public class CategoryMapper {

	public Category toEntity(CategoryRequestDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    public CategoryResponseDTO toResponseDTO(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName().getDisplayName()); 
        return dto;
    }

}
