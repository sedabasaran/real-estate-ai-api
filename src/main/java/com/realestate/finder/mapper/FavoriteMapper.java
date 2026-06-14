package com.realestate.finder.mapper;

import org.springframework.stereotype.Component;

import com.realestate.finder.dto.request.FavoriteRequestDTO;
import com.realestate.finder.dto.response.FavoriteResponseDTO;
import com.realestate.finder.entity.Favorite;
import com.realestate.finder.entity.Property;
import com.realestate.finder.entity.User;

@Component
public class FavoriteMapper {

	public Favorite toEntity(FavoriteRequestDTO requestDTO, User user, Property property) {
		Favorite favorite = new Favorite();
		favorite.setUser(user);
		favorite.setProperty(property);
		return favorite;
	}

	public FavoriteResponseDTO toResponseDTO(Favorite favorite) {
		return new FavoriteResponseDTO(favorite.getId(), favorite.getUser().getId(), favorite.getProperty().getId());
	}

}
