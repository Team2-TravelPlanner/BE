package com.laioffer.travelplanner.planModel;


import java.util.List;

public class CustomizedPlan {
    private Origin origin;

    private Integer StartDate;

    private Integer EndDate;

    private List<PlaceDetail> placeDetails;

    public CustomizedPlan() {
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public void setStartDate(Integer startDate) {
        StartDate = startDate;
    }

    public void setEndDate(Integer endDate) {
        EndDate = endDate;
    }

    public void setPlaceDetails(List<PlaceDetail> placeDetails) {
        this.placeDetails = placeDetails;
    }

    public Origin getOrigin() {
        return origin;
    }

    public Integer getStartDate() {
        return StartDate;
    }

    public Integer getEndDate() {
        return EndDate;
    }

    public List<PlaceDetail> getPlaceDetails() {
        return placeDetails;
    }
}
