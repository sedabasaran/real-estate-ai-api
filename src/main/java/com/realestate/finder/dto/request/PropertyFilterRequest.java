package com.realestate.finder.dto.request;

import java.math.BigDecimal;

import com.realestate.finder.entity.ListingType;

public class PropertyFilterRequest {
	
	private String city;
    private String district;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer minRoomCount;
    private Integer maxRoomCount;
    private Integer minSquareMeters;
    private Integer maxSquareMeters;
    private Long categoryId;
    private ListingType listingType;
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
	public BigDecimal getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Integer getMinRoomCount() {
		return minRoomCount;
	}
	public void setMinRoomCount(Integer minRoomCount) {
		this.minRoomCount = minRoomCount;
	}
	public Integer getMaxRoomCount() {
		return maxRoomCount;
	}
	public void setMaxRoomCount(Integer maxRoomCount) {
		this.maxRoomCount = maxRoomCount;
	}
	public Integer getMinSquareMeters() {
		return minSquareMeters;
	}
	public void setMinSquareMeters(Integer minSquareMeters) {
		this.minSquareMeters = minSquareMeters;
	}
	public Integer getMaxSquareMeters() {
		return maxSquareMeters;
	}
	public void setMaxSquareMeters(Integer maxSquareMeters) {
		this.maxSquareMeters = maxSquareMeters;
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
