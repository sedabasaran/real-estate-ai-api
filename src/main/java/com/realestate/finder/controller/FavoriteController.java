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

import com.realestate.finder.dto.request.FavoriteRequestDTO;
import com.realestate.finder.dto.response.FavoriteResponseDTO;
import com.realestate.finder.service.FavoriteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

	private final FavoriteService favoriteService;

	public FavoriteController(FavoriteService favoriteService) {
		this.favoriteService = favoriteService;
	}

	@PostMapping
	public ResponseEntity<FavoriteResponseDTO> addFavorite(@RequestBody @Valid FavoriteRequestDTO requestDTO) {
		FavoriteResponseDTO favorite = favoriteService.addFavorite(requestDTO.getUserId(), requestDTO.getPropertyId());
		return new ResponseEntity<>(favorite, HttpStatus.CREATED);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<List<FavoriteResponseDTO>> getFavoritesByUser(@PathVariable Long userId) {
		List<FavoriteResponseDTO> favorites = favoriteService.getFavoritesByUser(userId);
		return ResponseEntity.ok(favorites);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
		favoriteService.deleteFavorite(id);
		return ResponseEntity.noContent().build();
	}

}
