package com.laioffer.travelplanner.model.plan;

import com.laioffer.travelplanner.entities.Place;

public class PlaceDetailModel {
    private Long start;

    private Long end;

    private Place place;


    public PlaceDetailModel() {
    }


    public Long getStart() {
		return start;
	}


	public void setStart(Long start) {
		this.start = start;
	}


	public Long getEnd() {
		return end;
	}


	public void setEnd(Long end) {
		this.end = end;
	}


	public Place getPlace() {
        return place;
    }


    public void setPlace(Place place) {
        this.place = place;
    }
}
