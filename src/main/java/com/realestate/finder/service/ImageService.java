package com.realestate.finder.service;

import java.util.List;

import com.realestate.finder.dto.request.ImageRequestDTO;
import com.realestate.finder.dto.response.ImageResponseDTO;

public interface ImageService {

	ImageResponseDTO addImage(ImageRequestDTO requestDTO);

	List<ImageResponseDTO> getImagesByPropertyId(Long propertyId);

	void deleteImage(Long id);

}
