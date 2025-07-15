package com.realestate.finder.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.realestate.finder.dto.request.ImageRequestDTO;
import com.realestate.finder.dto.response.ImageResponseDTO;
import com.realestate.finder.entity.Image;
import com.realestate.finder.entity.Property;
import com.realestate.finder.exception.PropertyNotFoundException;
import com.realestate.finder.mapper.ImageMapper;
import com.realestate.finder.repository.ImageRepository;
import com.realestate.finder.repository.PropertyRepository;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final PropertyRepository propertyRepository;
    private final ImageMapper imageMapper;

    public ImageServiceImpl(ImageRepository imageRepository,
                            PropertyRepository propertyRepository,
                            ImageMapper imageMapper) {
        this.imageRepository = imageRepository;
        this.propertyRepository = propertyRepository;
        this.imageMapper = imageMapper;
    }

    @Override
    public ImageResponseDTO addImage(ImageRequestDTO requestDTO) {
        Property property = propertyRepository.findById(requestDTO.getPropertyId())
                .orElseThrow(() -> new PropertyNotFoundException("Property not found with id: " + requestDTO.getPropertyId()));

        Image image = imageMapper.toEntity(requestDTO, property);
        Image savedImage = imageRepository.save(image);
        return imageMapper.toResponseDTO(savedImage);
    }

    @Override
    public List<ImageResponseDTO> getImagesByPropertyId(Long propertyId) {
        return imageRepository.findByPropertyId(propertyId)
                .stream()
                .map(imageMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }




}
