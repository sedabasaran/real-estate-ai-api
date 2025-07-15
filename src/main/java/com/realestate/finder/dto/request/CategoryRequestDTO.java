package com.realestate.finder.dto.request;

import com.realestate.finder.entity.CategoryType;

public class CategoryRequestDTO {
	
    private CategoryType name;

    public CategoryType getName() {
        return name;
    }

    public void setName(CategoryType name) {
        this.name = name;
    }

}
