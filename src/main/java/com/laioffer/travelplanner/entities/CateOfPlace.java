package com.laioffer.travelplanner.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "cateofplace")

public class CateOfPlace {
	@Id
	private Integer cateOfPlaceId;
	private Integer placeId;
	private Integer categoryId;
	public Integer getCategoryId() {
		return categoryId;
	}
	public Integer getPlaceId() {
		return placeId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
}
