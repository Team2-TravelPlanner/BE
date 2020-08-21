package com.laioffer.travelplanner.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
	@Id
	private String categoryId;

	private String categoryName;
	private List<String> placeIds;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<String> getPlaceIds() {
		return placeIds;
	}

	public void setPlaceIds(List<String> placeIds) {
		this.placeIds = placeIds;
	}

}
