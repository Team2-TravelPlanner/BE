package com.laioffer.travelplanner.planModel;

import java.util.Date;
import java.util.List;

public class RecommendedPlan {
    private Origin origin;

    private Date StartDate;

    private Date EndDate;

    private List<PlaceDetail> placeDetails;

    public RecommendedPlan() {
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public void setPlaceDetails(List<PlaceDetail> placeDetails) {
        this.placeDetails = placeDetails;
    }

    public Origin getOrigin() {
        return origin;
    }

    public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	public Date getEndDate() {
		return EndDate;
	}

	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}

	public List<PlaceDetail> getPlaceDetails() {
        return placeDetails;
    }
}
