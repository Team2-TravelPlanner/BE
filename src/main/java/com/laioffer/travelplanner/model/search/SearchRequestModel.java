package com.laioffer.travelplanner.model.search;

import com.laioffer.travelplanner.model.common.AuthModel;

public class SearchRequestModel {

	private AuthModel authModel;

	// when the value is "", then search all with the geolocation
	private String query;

	// when the value is "", then search all with the geolocation
	private String category;

	private Double upperLeftLat;

	private Double upperLeftLon;

	private Double lowerRightLat;

	private Double lowerRightLon;

	// 10 by default
	private Integer displayItemLimit;

	// starting from page 0 by default
	private Integer currentPageNumber;

	public AuthModel getAuthModel() {
		return authModel;
	}

	public void setAuthModel(AuthModel authModel) {
		this.authModel = authModel;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getUpperLeftLat() {
		return upperLeftLat;
	}

	public void setUpperLeftLat(Double upperLeftLat) {
		this.upperLeftLat = upperLeftLat;
	}

	public Double getUpperLeftLon() {
		return upperLeftLon;
	}

	public void setUpperLeftLon(Double upperLeftLon) {
		this.upperLeftLon = upperLeftLon;
	}

	public Double getLowerRightLat() {
		return lowerRightLat;
	}

	public void setLowerRightLat(Double lowerRightLat) {
		this.lowerRightLat = lowerRightLat;
	}

	public Double getLowerRightLon() {
		return lowerRightLon;
	}

	public void setLowerRightLon(Double lowerRightLon) {
		this.lowerRightLon = lowerRightLon;
	}

	public Integer getCurrentPageNumber() {
		return currentPageNumber;
	}

	public Integer getDisplayItemLimit() {
		return displayItemLimit;
	}

	public void setDisplayItemLimit(Integer displayItemLimit) {
		this.displayItemLimit = displayItemLimit;
	}

	public void setCurrentPageNumber(Integer currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

}
