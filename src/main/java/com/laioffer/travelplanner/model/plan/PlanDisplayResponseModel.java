package com.laioffer.travelplanner.model.plan;

import java.util.List;

import com.laioffer.travelplanner.model.common.OperationResponse;

public class PlanDisplayResponseModel {

	
	private OperationResponse operationResponse;
	
	private List<PlanDisplayModel> planDisplayModel;

	public OperationResponse getOperationResponse() {
		return operationResponse;
	}

	public void setOperationResponse(OperationResponse operationResponse) {
		this.operationResponse = operationResponse;
	}

	public List<PlanDisplayModel> getPlanDisplayModel() {
		return planDisplayModel;
	}

	public void setPlanDisplayModel(List<PlanDisplayModel> planDisplayModel) {
		this.planDisplayModel = planDisplayModel;
	}
	
	
}
