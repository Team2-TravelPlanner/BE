package com.laioffer.travelplanner.services;

import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.plan.PlanSaveRequestModel;

import com.laioffer.travelplanner.planModel.RecommendedPlan;
import com.laioffer.travelplanner.requestModel.RequestSettingsModel;

import java.util.List;

public interface PlanService {

	public RecommendedPlan generateRecommendedPlan(List<String> names, List<String> categories, RequestSettingsModel settings);
}
