package com.laioffer.travelplanner.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.laioffer.travelplanner.enumerate.DayOfWeek;

@Document(indexName = "travel")
public class BusinessHour {
	@Id
	private String businessHourId;

	private String placeId;

	private String dayOfWeek;
	private String startTime;
	private String endTime;

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String  getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getBusinessHourId() {
		return businessHourId;
	}

	public void setBusinessHourId(String businessHourId) {
		this.businessHourId = businessHourId;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

}
