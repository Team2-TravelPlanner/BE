package com.laioffer.travelplanner.services;

import java.util.List;

import com.laioffer.travelplanner.model.common.AuthModel;
import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.plan.PlanDisplayResponseModel;
import com.laioffer.travelplanner.model.plan.PlanGetModel;
import com.laioffer.travelplanner.model.plan.PlanSaveRequestModel;

public interface PlanService {

	public OperationResponse savePlan(PlanSaveRequestModel model) throws Exception;
	
	public PlanDisplayResponseModel getPlan(PlanGetModel model) throws Exception;
	
	public PlanDisplayResponseModel getAllPlan(AuthModel model) throws Exception;
}
