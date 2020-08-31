package com.laioffer.travelplanner.model.plan;

import java.util.Date;
import java.util.List;

import com.laioffer.travelplanner.enumerate.TypeOfPlan;
import com.laioffer.travelplanner.model.common.AuthModel;

public class PlanDisplayModel {

	private Date startDate;
	
	private Date endDate;
	
	private Float startLatitude;
	
	private Float startLongitude;
	
	private TypeOfPlan typeOfPlan;
	
	private String planId;
	
	private List<DayOfPlanDisplayModel> dayOfPlanDisplayModels;
	
	
	public AuthModel getAuthModel() {
		return authModel;
	}


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

	public Float getStartLatitude() {
		return startLatitude;
	}

	public void setStartLatitude(Float startLatitude) {
		this.startLatitude = startLatitude;
	}

	public Float getStartLongitude() {
		return startLongitude;
	}

	public void setStartLongitude(Float startLongitude) {
		this.startLongitude = startLongitude;
	}

	public List<DayOfPlanDisplayModel> getDayOfPlanDisplayModels() {
		return dayOfPlanDisplayModels;
	}

	public void setDayOfPlanDisplayModels(List<DayOfPlanDisplayModel> dayOfPlanDisplayModels) {
		this.dayOfPlanDisplayModels = dayOfPlanDisplayModels;
	}
	
	
	
	
}
