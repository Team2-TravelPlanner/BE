package com.laioffer.travelplanner.model.plan;

import java.util.Date;
import java.util.List;

import com.laioffer.travelplanner.model.common.AuthModel;

public class PlanGetAllModel {
	
	private AuthModel authModel;
	
	private String planId;
	
	private String userName;
	
	private Float startLatitude;
	
	private Float startLongitude;
	
	private Date startDate;
	
	private Date endDate;
	
	private List<DayOfPlanSaveModel> dayOfPlanGetModels;

	public AuthModel getAuthModel() {
		return authModel;
	}

	public void setAuthModel(AuthModel authModel) {
		this.authModel = authModel;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public List<DayOfPlanSaveModel> getDayOfPlanGetModels() {
		return dayOfPlanGetModels;
	}

	public void setDayOfPlanGetModels(List<DayOfPlanSaveModel> dayOfPlanGetModels) {
		this.dayOfPlanGetModels = dayOfPlanGetModels;
	}
}
