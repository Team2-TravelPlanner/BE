package com.laioffer.travelplanner.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "places")

public class Place {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	
	public Place() {
		
	}
	
	private Place(PlaceBuilder builder) {
		this.placeId = builder.placeId;
		this.placeName = builder.placeName;
		this.address = builder.address;
		this.lat = builder.lat;
		this.lon = builder.lon;
		this.category = builder.category;
		this.imageLink = builder.imageLink;
		this.averageTime = builder.averageTime;
		this.intro = builder.intro;
		this.popularity = builder.popularity;
		
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
	
	
	@Override
	public String toString() {
		return "Place [placeId=" + placeId + ", placeName=" + placeName + ", address=" + address + ", lat=" + lat
				+ ", lon=" + lon + ", category=" + category + ", imageLink=" + imageLink + ", averageTime="
				+ averageTime + ", intro=" + intro + ", popularity=" + popularity + "]";
	}


	private static class PlaceBuilder{
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
		
		
		public void setPlaceId(Integer placeId) {
			this.placeId = placeId;
		}

		public void setPlaceName(String placeName) {
			this.placeName = placeName;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public void setLat(float lat) {
			this.lat = lat;
		}

		public void setLon(float lon) {
			this.lon = lon;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public void setImageLink(String imageLink) {
			this.imageLink = imageLink;
		}

		public void setAverageTime(float averageTime) {
			this.averageTime = averageTime;
		}

		public void setIntro(String intro) {
			this.intro = intro;
		}

		public void setPopularity(float popularity) {
			this.popularity = popularity;
		}

		public Place build() {
			return new Place(this);
		}
	}
	
}
