package com.laioffer.travelplanner.model.plan;

import java.util.Date;

import com.laioffer.travelplanner.model.common.AuthModel;

public class PlanGetModel {

	private AuthModel authModel;
	
	private String planId;
	
	

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
	
	
}
