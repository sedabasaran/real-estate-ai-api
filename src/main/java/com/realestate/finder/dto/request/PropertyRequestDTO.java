package com.realestate.finder.dto.request;

import java.math.BigDecimal;

import com.realestate.finder.entity.ListingType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PropertyRequestDTO {
	@NotNull(message = "Title cannot be null")
	@Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
	private String title;

	@NotNull(message = "Description cannot be null")
	@Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
	private String description;

	@NotNull(message = "Price cannot be null")
	@Min(value = 0, message = "Price must be greater than or equal to 0")
	private BigDecimal price;

	@NotNull(message = "City cannot be null")
	private String city;

	@NotNull(message = "District cannot be null")
	private String district;

	@NotNull(message = "Room count cannot be null")
	@Min(value = 1, message = "Room count must be at least 1")
	@Max(value = 20, message = "Room count must be no more than 20")
	private int roomCount;

	@NotNull(message = "Square meters cannot be null")
	@Min(value = 10, message = "Square meters must be at least 10")
	private int squareMeters;

	@NotNull(message = "Category ID cannot be null")
	private Long categoryId;

	@NotNull(message = "Category Listing Type cannot be null")
	private ListingType listingType;

	public PropertyRequestDTO() {
	}

	public PropertyRequestDTO(
			@NotNull(message = "Title cannot be null") @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters") String title,
			@NotNull(message = "Description cannot be null") @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters") String description,
			@NotNull(message = "Price cannot be null") @Min(value = 0, message = "Price must be greater than or equal to 0") BigDecimal price,
			@NotNull(message = "City cannot be null") String city,
			@NotNull(message = "District cannot be null") String district,
			@NotNull(message = "Room count cannot be null") @Min(value = 1, message = "Room count must be at least 1") @Max(value = 20, message = "Room count must be no more than 20") int roomCount,
			@NotNull(message = "Square meters cannot be null") @Min(value = 10, message = "Square meters must be at least 10") int squareMeters,
			@NotNull(message = "Category ID cannot be null") Long categoryId,
			@NotNull(message = "Category Listing Type cannot be null") ListingType listingType) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.city = city;
		this.district = district;
		this.roomCount = roomCount;
		this.squareMeters = squareMeters;
		this.categoryId = categoryId;
		this.listingType = listingType;
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public ListingType getListingType() {
		return listingType;
	}

	public void setListingType(ListingType listingType) {
		this.listingType = listingType;
	}

}
