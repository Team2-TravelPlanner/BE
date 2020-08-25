package com.laioffer.travelplanner.services;

import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.plan.PlanSaveRequestModel;

public interface PlanService {

	public OperationResponse savePlan(PlanSaveRequestModel model) throws Exception;
}
