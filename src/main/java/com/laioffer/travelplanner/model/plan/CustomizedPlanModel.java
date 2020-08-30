package com.laioffer.travelplanner.model.plan;


import java.util.List;

public class CustomizedPlanModel {
    private OriginPlanModel originPlanModel;

    private Integer StartDate;

    private Integer EndDate;

    private List<PlaceDetailModel> placeDetailModels;

    public CustomizedPlanModel() {
    }

    public void setOriginPlanModel(OriginPlanModel originPlanModel) {
        this.originPlanModel = originPlanModel;
    }

    public void setStartDate(Integer startDate) {
        StartDate = startDate;
    }

    public void setEndDate(Integer endDate) {
        EndDate = endDate;
    }

    public void setPlaceDetailModels(List<PlaceDetailModel> placeDetailModels) {
        this.placeDetailModels = placeDetailModels;
    }

    public OriginPlanModel getOriginPlanModel() {
        return originPlanModel;
    }

    public Integer getStartDate() {
        return StartDate;
    }

    public Integer getEndDate() {
        return EndDate;
    }

    public List<PlaceDetailModel> getPlaceDetailModels() {
        return placeDetailModels;
    }
}
