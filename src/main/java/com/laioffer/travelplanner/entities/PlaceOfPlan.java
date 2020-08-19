package com.laioffer.travelplanner.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table (name = "placeOfPlan")
@IdClass(PlaceOfPlanId.class)
public class PlaceOfPlan {
	@Id
	private Integer placeId;
	@Id
	private Integer dayId;
	private Date startTime;
	private Date endTime;
	
	public PlaceOfPlan(Integer placeId, Integer dayId) {
		this.placeId = placeId;
		this.dayId = dayId;
	}

	public Integer getDayId() {
		return dayId;
	}


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

	public Integer getPlaceId() {
		return placeId;
	}
	
	
}

