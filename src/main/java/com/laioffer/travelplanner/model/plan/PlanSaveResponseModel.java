package com.laioffer.travelplanner.model.plan;

import com.laioffer.travelplanner.model.common.OperationResponse;

public class PlanSaveResponseModel {

	private String planId;
	
	private OperationResponse operationResponse;

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public OperationResponse getOperationResponse() {
		return operationResponse;
	}

	public void setOperationResponse(OperationResponse operationResponse) {
		this.operationResponse = operationResponse;
	}
	
}
