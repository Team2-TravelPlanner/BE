package com.laioffer.travelplanner.model.plan;

import java.util.Date;

public class PlaceOfPlanSaveModel {
	
	private Date startDate;
	
	private Date endDate;
	
	private String placeId;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

}
