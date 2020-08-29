package com.laioffer.travelplanner.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

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

}
