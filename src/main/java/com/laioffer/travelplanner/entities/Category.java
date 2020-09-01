package com.laioffer.travelplanner.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "travel")
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

	@Override
	public String toString() {
		return "Category{" +
				"categoryId='" + categoryId + '\'' +
				", categoryName='" + categoryName + '\'' +
				", placeIds=" + placeIds +
				'}';
	}
}
