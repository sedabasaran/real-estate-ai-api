package com.realestate.finder.dto.response;

public class FavoriteResponseDTO {

	private Long id;
	private Long userId;
	private Long propertyId;

	public FavoriteResponseDTO() {
	}

	public FavoriteResponseDTO(Long id, Long userId, Long propertyId) {
		this.id = id;
		this.userId = userId;
		this.propertyId = propertyId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
