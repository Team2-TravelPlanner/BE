package com.laioffer.travelplanner.model.plan;

import java.util.Date;
import java.util.List;

import com.laioffer.travelplanner.enumerate.TypeOfPlan;
import com.laioffer.travelplanner.model.common.AuthModel;

public class PlanDisplayModel {

	private Date startDate;
	
	private Date endDate;
	
	private Double startLatitude;
	
	private Double startLongitude;
	
	private TypeOfPlan typeOfPlan;
	
	private String planId;
	
	private List<DayOfPlanDisplayModel> dayOfPlanDisplayModels;

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public TypeOfPlan getTypeOfPlan() {
		return typeOfPlan;
	}

	public void setTypeOfPlan(TypeOfPlan typeOfPlan) {
		this.typeOfPlan = typeOfPlan;
	}

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

	public List<DayOfPlanDisplayModel> getDayOfPlanDisplayModels() {
		return dayOfPlanDisplayModels;
	}

	public void setDayOfPlanDisplayModels(List<DayOfPlanDisplayModel> dayOfPlanDisplayModels) {
		this.dayOfPlanDisplayModels = dayOfPlanDisplayModels;
	}

	public Double getStartLatitude() {
		return startLatitude;
	}

	public void setStartLatitude(Double startLatitude) {
		this.startLatitude = startLatitude;
	}

	public Double getStartLongitude() {
		return startLongitude;
	}

	public void setStartLongitude(Double startLongitude) {
		this.startLongitude = startLongitude;
	}
	
}
