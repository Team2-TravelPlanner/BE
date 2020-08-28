package com.laioffer.travelplanner.model.search;

import java.util.List;

import com.laioffer.travelplanner.model.common.OperationResponse;

public class SearchResponseModel {
	
	private OperationResponse operationResponse;
	
	private Integer currentPageNumber;
	
	private Integer maxPageNumber;
	
	private List<PlaceInfoModel> placeInfoModels;

	public OperationResponse getOperationResponse() {
		return operationResponse;
	}

	public void setOperationResponse(OperationResponse operationResponse) {
		this.operationResponse = operationResponse;
	}

	public Integer getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(Integer currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public Integer getMaxPageNumber() {
		return maxPageNumber;
	}

	public void setMaxPageNumber(Integer maxPageNumber) {
		this.maxPageNumber = maxPageNumber;
	}

	public List<PlaceInfoModel> getPlaceInfoModels() {
		return placeInfoModels;
	}

	public void setPlaceInfoModels(List<PlaceInfoModel> placeInfoModels) {
		this.placeInfoModels = placeInfoModels;
	}
	
	

}
