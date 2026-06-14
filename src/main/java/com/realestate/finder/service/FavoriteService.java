package com.realestate.finder.service;

import java.util.List;

import com.realestate.finder.dto.response.FavoriteResponseDTO;

public interface FavoriteService {

	FavoriteResponseDTO addFavorite(Long userId, Long propertyId);

	List<FavoriteResponseDTO> getFavoritesByUser(Long userId);

	void deleteFavorite(Long id);
}
