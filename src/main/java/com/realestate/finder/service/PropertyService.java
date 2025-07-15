package com.realestate.finder.service;

import java.util.List;

import com.realestate.finder.dto.request.PropertyRequestDTO;
import com.realestate.finder.dto.response.PropertyResponseDTO;
import com.realestate.finder.entity.User;

public interface PropertyService {

	PropertyResponseDTO createProperty(PropertyRequestDTO request, User user);

	List<PropertyResponseDTO> getAllProperties();

	PropertyResponseDTO getPropertyById(Long id);

	PropertyResponseDTO updateProperty(Long id, PropertyRequestDTO propertyRequestDTO);

	void deleteProperty(Long id);

}
