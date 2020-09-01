package com.laioffer.travelplanner.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "travel")
public class Place {
	@Id
	private String placeId;

	private String placeName;
	private String address;
	private double lat;
	private double lon;

	private String imageLink;
	private float averageTime;
	private String intro;
	private float popularity;
	private String website;
	private List<String> businesshourIds;
	private List<String> categories;

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

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
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

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public void setLon(Float lon) {
		this.lon = lon;
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

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Place [placeId=" + placeId + ", placeName=" + placeName + ", address=" + address + ", lat=" + lat
				+ ", lon=" + lon + ", imageLink=" + imageLink + ", averageTime=" + averageTime + ", intro=" + intro
				+ ", popularity=" + popularity + "]";
	}

}
