package com.realestate.finder.entity;

public enum CategoryType {

	APARTMENT("Daire"), HOUSE("Müstakil Ev"), VILLA("Villa"), OFFICE("Ofis"), LAND("Arsa");

	private final String displayName;

	CategoryType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

}
