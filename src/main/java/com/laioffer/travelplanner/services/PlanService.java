package com.laioffer.travelplanner.services;

import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.plan.PlanGetAllModel;
import com.laioffer.travelplanner.model.plan.PlanGetModel;
import com.laioffer.travelplanner.model.plan.PlanSaveRequestModel;

public interface PlanService {

	public OperationResponse savePlan(PlanSaveRequestModel model) throws Exception;
	
	public OperationResponse getPlan(PlanGetModel model) throws Exception;
	
	public OperationResponse getAllPlan(PlanGetAllModel model) throws Exception;
}
