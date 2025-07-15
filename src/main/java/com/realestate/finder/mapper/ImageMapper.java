package com.realestate.finder.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.realestate.finder.dto.request.ImageRequestDTO;
import com.realestate.finder.dto.response.ImageResponseDTO;
import com.realestate.finder.entity.Image;
import com.realestate.finder.entity.Property;

@Component
public class ImageMapper {

	public Image toEntity(ImageRequestDTO dto, Property property) {
		return new Image(dto.getUrl(), LocalDateTime.now(), property);
	}

	public ImageResponseDTO toResponseDTO(Image image) {
		ImageResponseDTO dto = new ImageResponseDTO();
		dto.setId(image.getId());
		dto.setUrl(image.getUrl());
		dto.setUploadedAt(image.getUploadedAt());
		return dto;
	}

}
