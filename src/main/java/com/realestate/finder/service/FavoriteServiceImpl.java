package com.realestate.finder.service;

import org.springframework.stereotype.Service;

import com.realestate.finder.dto.response.FavoriteResponseDTO;
import com.realestate.finder.entity.Favorite;
import com.realestate.finder.entity.Property;
import com.realestate.finder.entity.User;
import com.realestate.finder.exception.FavoriteNotFoundException;
import com.realestate.finder.exception.PropertyNotFoundException;
import com.realestate.finder.exception.UserNotFoundException;
import com.realestate.finder.mapper.FavoriteMapper;
import com.realestate.finder.repository.FavoriteRepository;
import com.realestate.finder.repository.PropertyRepository;
import com.realestate.finder.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final FavoriteMapper favoriteMapper;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, UserRepository userRepository,
                               PropertyRepository propertyRepository, FavoriteMapper favoriteMapper) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
        this.favoriteMapper = favoriteMapper;
    }

    @Override
    public FavoriteResponseDTO addFavorite(Long userId, Long propertyId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new PropertyNotFoundException("Property not found with id: " + propertyId));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProperty(property);

        Favorite savedFavorite = favoriteRepository.save(favorite);
        return favoriteMapper.toResponseDTO(savedFavorite);
    }

    @Override
    public List<FavoriteResponseDTO> getFavoritesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        List<Favorite> favorites = favoriteRepository.findByUser(user);

        return favorites.stream()
                .map(favoriteMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFavorite(Long id) {
        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() -> new FavoriteNotFoundException("Favorite not found with id: " + id));
        favoriteRepository.delete(favorite);
    }
}
