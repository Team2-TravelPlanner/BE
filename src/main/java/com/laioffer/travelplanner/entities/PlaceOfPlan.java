package com.laioffer.travelplanner.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "travel")
public class PlaceOfPlan {
	@Id
	private String placeOfPlanId;
	private String dayOfPlanId;
	private String placeId;
	private Date startTime;
	private Date endTime;

	// .. info

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getPlaceOfPlanId() {
		return placeOfPlanId;
	}

	public void setPlaceOfPlanId(String placeOfPlanId) {
		this.placeOfPlanId = placeOfPlanId;
	}

	public String getDayOfPlanId() {
		return dayOfPlanId;
	}

	public void setDayOfPlanId(String dayOfPlanId) {
		this.dayOfPlanId = dayOfPlanId;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

}
