package com.realestate.finder.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.realestate.finder.dto.request.PropertyRequestDTO;
import com.realestate.finder.dto.response.PropertyResponseDTO;
import com.realestate.finder.entity.Category;
import com.realestate.finder.entity.Property;

@Component
public class PropertyMapper {

	public Property toEntity(PropertyRequestDTO dto, Category category) {
		Property property = new Property();
		property.setTitle(dto.getTitle());
		property.setDescription(dto.getDescription());
		property.setPrice(dto.getPrice());
		property.setCity(dto.getCity());
		property.setDistrict(dto.getDistrict());
		property.setRoomCount(dto.getRoomCount());
		property.setSquareMeters(dto.getSquareMeters());
		property.setCreatedAt(LocalDateTime.now());
		property.setCategory(category); 
		return property;
	}

	public void updateEntity(Property property, PropertyRequestDTO dto, Category category) {
		property.setTitle(dto.getTitle());
		property.setDescription(dto.getDescription());
		property.setPrice(dto.getPrice());
		property.setCity(dto.getCity());
		property.setDistrict(dto.getDistrict());
		property.setRoomCount(dto.getRoomCount());
		property.setSquareMeters(dto.getSquareMeters());
		property.setCategory(category); 
	}

	public PropertyResponseDTO toResponseDTO(Property property) {
		PropertyResponseDTO dto = new PropertyResponseDTO();
		dto.setId(property.getId());
		dto.setTitle(property.getTitle());
		dto.setDescription(property.getDescription());
		dto.setPrice(property.getPrice());
		dto.setCity(property.getCity());
		dto.setDistrict(property.getDistrict());
		dto.setRoomCount(property.getRoomCount());
		dto.setSquareMeters(property.getSquareMeters());
		dto.setCreatedAt(property.getCreatedAt());
		dto.setCategoryName(property.getCategory().getName()); // response'da gösterilecek alan
		return dto;
	}
}
