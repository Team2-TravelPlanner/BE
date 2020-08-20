package com.laioffer.travelplanner.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "places")

public class Place {
	@Id
	private Integer placeId;
	private String placeName;
	private String address;
	private float lat;
	private float lon;
	private String category;
	private String imageLink;
	private float averageTime;
	private String intro;
	private float popularity;
	private List<BusinessHour> businesshours;
	private List<Category> categories;
	


	public Place() {
		
	}
	
	public Integer getPlaceId() {
		return placeId;
	}
	public void setPlaceId(Integer placeId) {
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
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public List<BusinessHour> getBusinesshours() {
		return businesshours;
	}
	public void setBusinesshours(List<BusinessHour> businesshours) {
		this.businesshours = businesshours;
	}
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	@Override
	public String toString() {
		return "Place [placeId=" + placeId + ", placeName=" + placeName + ", address=" + address + ", lat=" + lat
				+ ", lon=" + lon + ", category=" + category + ", imageLink=" + imageLink + ", averageTime="
				+ averageTime + ", intro=" + intro + ", popularity=" + popularity + "]";
	}

	

}
