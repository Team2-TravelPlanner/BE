package com.laioffer.travelplanner.model.plan;

import java.util.Date;

import com.laioffer.travelplanner.model.common.AuthModel;

public class PlanGetModel {

	private AuthModel authModel;
	
	private String planId;
	
	private String userName;
	
	private Date startDate;
	
	private Date endDate;

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
	
	
}
