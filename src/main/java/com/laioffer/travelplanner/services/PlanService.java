package com.laioffer.travelplanner.services;


import com.laioffer.travelplanner.planModel.CustomizedPlan;
import com.laioffer.travelplanner.requestModel.RequestSettingsModel;

import java.util.List;


public interface PlanService {
    public CustomizedPlan generateCustomizedPlan(List<String> names, List<String> categories, RequestSettingsModel settings);
}
