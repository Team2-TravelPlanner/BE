package com.laioffer.travelplanner.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Document(indexName = "travel")
public class Place {
	@Id
	private String placeId;

	private String placeName;
	private String address;
	private GeoPoint location;

	private String imageLink;
	private Float averageTime;
	private String intro;
	private Float popularity;
	private String website;
	private List<String> businesshourIds;
	private List<String> categoryIds;

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public GeoPoint getLocation() {
		return location;
	}

	public void setLocation(GeoPoint location) {
		this.location = location;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public float getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(float averageTime) {
		this.averageTime = averageTime;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public float getPopularity() {
		return popularity;
	}

	public void setPopularity(float popularity) {
		this.popularity = popularity;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public void setAverageTime(Float averageTime) {
		this.averageTime = averageTime;
	}

	public void setPopularity(Float popularity) {
		this.popularity = popularity;
	}

	public List<String> getBusinesshourIds() {
		return businesshourIds;
	}

	public void setBusinesshourIds(List<String> businesshourIds) {
		this.businesshourIds = businesshourIds;
	}

	public List<String> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<String> categoryIds) {
		this.categoryIds = categoryIds;
	}

	@Override
	public String toString() {
		return "Place [placeId=" + placeId + ", placeName=" + placeName + ", address=" + address + ", lat=" + location.getLat()
				+ ", lon=" + location.getLon() + ", imageLink=" + imageLink + ", averageTime=" + averageTime + ", intro=" + intro
				+ ", popularity=" + popularity + "]";
	}

}
