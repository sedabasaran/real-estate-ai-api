package com.realestate.finder.dto.request;

import jakarta.validation.constraints.NotNull;

public class FavoriteRequestDTO {
	
	@NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Property ID cannot be null")
    private Long propertyId;

    public FavoriteRequestDTO() {
    }

    public FavoriteRequestDTO(Long userId, Long propertyId) {
        this.userId = userId;
        this.propertyId = propertyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

}
