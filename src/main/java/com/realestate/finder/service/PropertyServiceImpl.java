package com.realestate.finder.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.realestate.finder.dto.request.PropertyRequestDTO;
import com.realestate.finder.dto.response.PropertyResponseDTO;
import com.realestate.finder.entity.Category;
import com.realestate.finder.entity.Property;
import com.realestate.finder.entity.User;
import com.realestate.finder.exception.PropertyNotFoundException;
import com.realestate.finder.exception.ResourceNotFoundException;
import com.realestate.finder.mapper.PropertyMapper;
import com.realestate.finder.repository.CategoryRepository;
import com.realestate.finder.repository.PropertyRepository;

@Service
public class PropertyServiceImpl implements PropertyService {

	private final PropertyRepository propertyRepository;
    private final CategoryRepository categoryRepository;
    private final PropertyMapper propertyMapper;

    public PropertyServiceImpl(PropertyRepository propertyRepository,
                                CategoryRepository categoryRepository,
                                PropertyMapper propertyMapper) {
        this.propertyRepository = propertyRepository;
        this.categoryRepository = categoryRepository;
        this.propertyMapper = propertyMapper;
    }

    public PropertyResponseDTO createProperty(PropertyRequestDTO request, User user) {
        Property property = new Property();

        property.setTitle(request.getTitle());
        property.setDescription(request.getDescription());
        property.setPrice(request.getPrice());
        property.setCity(request.getCity());
        property.setDistrict(request.getDistrict());
        property.setRoomCount(request.getRoomCount());
        property.setSquareMeters(request.getSquareMeters());
        property.setCreatedAt(LocalDateTime.now());
        property.setUser(user);
        property.setListingType(request.getListingType());


        Category category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category not found"));
        property.setCategory(category);

        Property saved = propertyRepository.save(property);
        return propertyMapper.toResponseDTO(saved);
    }


    @Override
    public List<PropertyResponseDTO> getAllProperties() {
        List<Property> properties = propertyRepository.findAll();
        return properties.stream()
                .map(propertyMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PropertyResponseDTO getPropertyById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new PropertyNotFoundException("Property not found with id: " + id));
        return propertyMapper.toResponseDTO(property);
    }

    @Override
    public PropertyResponseDTO updateProperty(Long id, PropertyRequestDTO propertyRequestDTO) {
        Property existingProperty = propertyRepository.findById(id)
                .orElseThrow(() -> new PropertyNotFoundException("Property not found with id: " + id));

        Category category = categoryRepository.findById(propertyRequestDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + propertyRequestDTO.getCategoryId()));

        propertyMapper.updateEntity(existingProperty, propertyRequestDTO, category);
        Property updatedProperty = propertyRepository.save(existingProperty);
        return propertyMapper.toResponseDTO(updatedProperty);
    }

    @Override
    public void deleteProperty(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new PropertyNotFoundException("Property not found with id: " + id));
        propertyRepository.delete(property);
    }
}
