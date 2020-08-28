package com.laioffer.travelplanner.model.plan;

import java.util.List;

public class DayOfPlanSaveModel {

	private Integer index;
	
	private List<PlaceOfPlanSaveModel> placeOfPlanSaveModels;

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public List<PlaceOfPlanSaveModel> getPlaceOfPlanSaveModels() {
		return placeOfPlanSaveModels;
	}

	public void setPlaceOfPlanSaveModels(List<PlaceOfPlanSaveModel> placeOfPlanSaveModels) {
		this.placeOfPlanSaveModels = placeOfPlanSaveModels;
	}
	
	
}
