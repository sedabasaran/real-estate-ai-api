package com.realestate.finder.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.finder.dto.request.CategoryRequestDTO;
import com.realestate.finder.dto.response.CategoryResponseDTO;
import com.realestate.finder.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping
	public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO requestDTO) {
		CategoryResponseDTO responseDTO = categoryService.createCategory(requestDTO);
		return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
		List<CategoryResponseDTO> categories = categoryService.getAllCategories();
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
		CategoryResponseDTO category = categoryService.getCategoryById(id);
		return ResponseEntity.ok(category);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id,
			@Valid @RequestBody CategoryRequestDTO requestDTO) {
		CategoryResponseDTO updatedCategory = categoryService.updateCategory(id, requestDTO);
		return ResponseEntity.ok(updatedCategory);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}

}
