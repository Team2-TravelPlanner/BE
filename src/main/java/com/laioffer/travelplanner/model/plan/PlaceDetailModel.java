package com.laioffer.travelplanner.model.plan;

import com.laioffer.travelplanner.entities.Place;

public class PlaceDetailModel {
    private Integer start;

    private Integer end;

    private Place place;


    public PlaceDetailModel() {
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    public Place getPlace() {
        return place;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
