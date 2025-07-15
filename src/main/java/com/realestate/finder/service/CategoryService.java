package com.realestate.finder.service;

import java.util.List;

import com.realestate.finder.dto.request.CategoryRequestDTO;
import com.realestate.finder.dto.response.CategoryResponseDTO;

public interface CategoryService {
	
	CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);

    List<CategoryResponseDTO> getAllCategories();

    CategoryResponseDTO getCategoryById(Long id);

    void deleteCategory(Long id);

    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO);
}
