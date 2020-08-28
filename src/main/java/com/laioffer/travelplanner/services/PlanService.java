package com.laioffer.travelplanner.services;

import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.common.SettingsRequestModel;
import com.laioffer.travelplanner.model.plan.CustomizedPlanModel;
import com.laioffer.travelplanner.model.plan.PlanSaveRequestModel;

import java.util.List;

public interface PlanService {

	public OperationResponse savePlan(PlanSaveRequestModel model) throws Exception;

	public CustomizedPlanModel generateCustomizedPlan(List<String> names, List<String> categories, SettingsRequestModel settings);
}
