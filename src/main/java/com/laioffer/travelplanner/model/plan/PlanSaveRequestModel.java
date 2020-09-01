package com.laioffer.travelplanner.model.plan;

import java.util.Date;
import java.util.List;

import com.laioffer.travelplanner.enumerate.TypeOfPlan;
import com.laioffer.travelplanner.model.common.AuthModel;

public class PlanSaveRequestModel {
	
	private AuthModel authModel;
	
	private Date startDate;
	
	private Date endDate;
	
	private Double startLatitude;
	
	private Double startLongitude;
	
	private TypeOfPlan typeOfPlan;
	
	private List<DayOfPlanSaveModel> dayOfPlanSaveModels;
	

	public AuthModel getAuthModel() {
		return authModel;
	}

	public void setAuthModel(AuthModel authModel) {
		this.authModel = authModel;
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

	public TypeOfPlan getTypeOfPlan() {
		return typeOfPlan;
	}

	public void setTypeOfPlan(TypeOfPlan typeOfPlan) {
		this.typeOfPlan = typeOfPlan;
	}

	public List<DayOfPlanSaveModel> getDayOfPlanSaveModels() {
		return dayOfPlanSaveModels;
	}

	public void setDayOfPlanSaveModels(List<DayOfPlanSaveModel> dayOfPlanSaveModels) {
		this.dayOfPlanSaveModels = dayOfPlanSaveModels;
	}
	

}
