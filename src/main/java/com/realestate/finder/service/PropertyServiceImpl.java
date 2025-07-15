package com.realestate.finder.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.realestate.finder.dto.request.PropertyFilterRequest;
import com.realestate.finder.specification.PropertySpecification;
import com.realestate.finder.dto.request.PropertyRequestDTO;
import com.realestate.finder.dto.response.PropertyResponseDTO;
import com.realestate.finder.entity.Category;
import com.realestate.finder.entity.Property;
import com.realestate.finder.entity.User;
import com.realestate.finder.exception.BadRequestException;
import com.realestate.finder.repository.CategoryRepository;
import com.realestate.finder.repository.PropertyRepository;

@Service
public class PropertyServiceImpl implements PropertyService {

	private final PropertyRepository propertyRepository;
	private final CategoryRepository categoryRepository;
	private final HuggingFaceService huggingFaceService;

	public PropertyServiceImpl(PropertyRepository propertyRepository, CategoryRepository categoryRepository,
			HuggingFaceService huggingFaceService) {
		this.propertyRepository = propertyRepository;
		this.categoryRepository = categoryRepository;
		this.huggingFaceService = huggingFaceService;
	}

	@Override
	public PropertyResponseDTO createProperty(PropertyRequestDTO request, User user) {

		boolean isAppropriate = huggingFaceService.isTextAppropriate(request.getDescription());
		boolean isScam = huggingFaceService.containsScamKeywords(request.getDescription());

		if (!isAppropriate || isScam) {
			throw new BadRequestException("İlan açıklaması uygunsuz içerik içeriyor veya dolandırıcılık şüphesi var.");
		}

		Category category = categoryRepository.findById(request.getCategoryId())
				.orElseThrow(() -> new BadRequestException("Kategori bulunamadı"));

		Property property = new Property();
		property.setTitle(request.getTitle());
		property.setDescription(request.getDescription());
		property.setPrice(request.getPrice());
		property.setCity(request.getCity());
		property.setDistrict(request.getDistrict());
		property.setRoomCount(request.getRoomCount());
		property.setSquareMeters(request.getSquareMeters());
		property.setListingType(request.getListingType());
		property.setCategory(category);
		property.setUser(user);
		property.setCreatedAt(LocalDateTime.now());

		Property saved = propertyRepository.save(property);

		return new PropertyResponseDTO(saved.getId(), saved.getTitle(), saved.getDescription(), saved.getPrice(),
				saved.getCity(), saved.getDistrict(), saved.getRoomCount(), saved.getSquareMeters(),
				saved.getCreatedAt(), null, saved.getCategory().getName(), saved.getListingType());
	}

	@Override
	public List<PropertyResponseDTO> getAllProperties() {
		return propertyRepository.findAll().stream()
				.map(p -> new PropertyResponseDTO(p.getId(), p.getTitle(), p.getDescription(), p.getPrice(),
						p.getCity(), p.getDistrict(), p.getRoomCount(), p.getSquareMeters(), p.getCreatedAt(), null,
						p.getCategory().getName(), p.getListingType()))
				.collect(Collectors.toList());
	}

	@Override
	public PropertyResponseDTO getPropertyById(Long id) {
		Property p = propertyRepository.findById(id).orElseThrow(() -> new BadRequestException("İlan bulunamadı"));

		return new PropertyResponseDTO(p.getId(), p.getTitle(), p.getDescription(), p.getPrice(), p.getCity(),
				p.getDistrict(), p.getRoomCount(), p.getSquareMeters(), p.getCreatedAt(), null,
				p.getCategory().getName(), p.getListingType());
	}

	@Override
	public PropertyResponseDTO updateProperty(Long id, PropertyRequestDTO requestDTO) {
		Property property = propertyRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("İlan bulunamadı"));

		property.setTitle(requestDTO.getTitle());
		property.setDescription(requestDTO.getDescription());
		property.setPrice(requestDTO.getPrice());
		property.setCity(requestDTO.getCity());
		property.setDistrict(requestDTO.getDistrict());
		property.setRoomCount(requestDTO.getRoomCount());
		property.setSquareMeters(requestDTO.getSquareMeters());
		property.setListingType(requestDTO.getListingType());

		Property updated = propertyRepository.save(property);

		return new PropertyResponseDTO(updated.getId(), updated.getTitle(), updated.getDescription(),
				updated.getPrice(), updated.getCity(), updated.getDistrict(), updated.getRoomCount(),
				updated.getSquareMeters(), updated.getCreatedAt(), null, updated.getCategory().getName(),
				updated.getListingType());
	}

	@Override
	public void deleteProperty(Long id) {
		propertyRepository.deleteById(id);
	}


	@Override
	public List<PropertyResponseDTO> filterProperties(PropertyFilterRequest filterRequest) {
		Specification<Property> spec = PropertySpecification.filterBy(filterRequest);

		return propertyRepository.findAll(spec).stream()
				.map(p -> new PropertyResponseDTO(p.getId(), p.getTitle(), p.getDescription(), p.getPrice(),
						p.getCity(), p.getDistrict(), p.getRoomCount(), p.getSquareMeters(), p.getCreatedAt(), null,
						p.getCategory().getName(), p.getListingType()))
				.collect(Collectors.toList());
	}
}
