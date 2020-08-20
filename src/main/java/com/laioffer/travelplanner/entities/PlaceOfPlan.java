package com.laioffer.travelplanner.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "placeOfPlan")
public class PlaceOfPlan {
	@Id
	private Integer placeOfPlanId;
	private Integer dayOfPlanId;
	private Integer placeId;
	private Date startTime;
	private Date endTime;
	
	public PlaceOfPlan() {
		
	}
	

	public Integer getPlaceOfPlanId() {
		return placeOfPlanId;
	}
	
	public void setPlaceOfPlanId(Integer placeOfPlanId) {
		this.placeOfPlanId = placeOfPlanId;
	}

	public Integer getDayOfPlanId() {
		return dayOfPlanId;
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

