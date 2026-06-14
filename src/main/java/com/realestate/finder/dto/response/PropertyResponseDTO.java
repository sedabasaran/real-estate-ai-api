package com.realestate.finder.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.realestate.finder.dto.request.ImageRequestDTO;
import com.realestate.finder.entity.CategoryType;
import com.realestate.finder.entity.ListingType;

public class PropertyResponseDTO {
	private Long id;
	private String title;
	private String description;
	private BigDecimal price;
	private String city;
	private String district;
	private int roomCount;
	private int squareMeters;
	private LocalDateTime createdAt;
	private List<ImageRequestDTO> images;
	private CategoryType categoryName;
	private ListingType listingType;

	public PropertyResponseDTO() {
	}

	public PropertyResponseDTO(Long id, String title, String description, BigDecimal price, String city,
			String district, int roomCount, int squareMeters, LocalDateTime createdAt, List<ImageRequestDTO> images,
			CategoryType categoryName, ListingType listingType) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.city = city;
		this.district = district;
		this.roomCount = roomCount;
		this.squareMeters = squareMeters;
		this.createdAt = createdAt;
		this.images = images;
		this.categoryName = categoryName;
		this.listingType = listingType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}

	public int getSquareMeters() {
		return squareMeters;
	}

	public void setSquareMeters(int squareMeters) {
		this.squareMeters = squareMeters;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<ImageRequestDTO> getImages() {
		return images;
	}

	public void setImages(List<ImageRequestDTO> images) {
		this.images = images;
	}
	
	public CategoryType getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(CategoryType categoryName) {
		this.categoryName = categoryName;
	}

	public ListingType getListingType() {
		return listingType;
	}

	public void setListingType(ListingType listingType) {
		this.listingType = listingType;
	}


}
