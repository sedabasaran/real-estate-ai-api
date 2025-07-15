package com.realestate.finder.entity;

public enum ListingType {
	
    FOR_SALE("Satılık"),
    FOR_RENT("Kiralık");

    private final String displayName;

    ListingType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
