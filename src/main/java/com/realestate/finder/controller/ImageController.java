package com.realestate.finder.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.finder.dto.request.ImageRequestDTO;
import com.realestate.finder.dto.response.ImageResponseDTO;
import com.realestate.finder.service.ImageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/images")
public class ImageController {

	private final ImageService imageService;

	public ImageController(ImageService imageService) {
		this.imageService = imageService;
	}

	@PostMapping
	public ResponseEntity<ImageResponseDTO> addImage(@Valid @RequestBody ImageRequestDTO requestDTO) {
		ImageResponseDTO response = imageService.addImage(requestDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/property/{propertyId}")
	public ResponseEntity<List<ImageResponseDTO>> getImagesByProperty(@PathVariable Long propertyId) {
		List<ImageResponseDTO> images = imageService.getImagesByPropertyId(propertyId);
		return ResponseEntity.ok(images);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
		imageService.deleteImage(id);
		return ResponseEntity.noContent().build();
	}

}
