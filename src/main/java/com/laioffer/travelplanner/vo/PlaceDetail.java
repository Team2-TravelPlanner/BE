package com.laioffer.travelplanner.vo;

import com.laioffer.travelplanner.entities.Place;
import io.swagger.models.auth.In;

import java.util.Date;

public class PlaceDetail {
    private Integer start;

    private Integer end;

    private Place place;


    public PlaceDetail() {
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
