package com.laioffer.travelplanner.model.search;

public class SearchRequestModel {

	//"" search all 
	private String query;
	
	
	//"" ALL  search all
	private String category;
	
	private Double upperLeftLat;
	
	private Double upperLeftLon;
	
	private Double lowerRightLat;
	
	private Double lowerRightLon;
	
	private Integer currentPageNumber;

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

	public void setCurrentPageNumber(Integer currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}
	
}
