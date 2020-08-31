package com.laioffer.travelplanner.model.plan;

import java.util.List;

public class DayOfPlanDisplayModel {
	
	private Integer index;
	
	private List<PlaceOfPlanDetailModel> placeOfPlanDetailModels;

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public List<PlaceOfPlanDetailModel> getPlaceOfPlanDetailModels() {
		return placeOfPlanDetailModels;
	}

	public void setPlaceOfPlanDetailModels(List<PlaceOfPlanDetailModel> placeOfPlanDetailModels) {
		this.placeOfPlanDetailModels = placeOfPlanDetailModels;
	}
	
	
}
