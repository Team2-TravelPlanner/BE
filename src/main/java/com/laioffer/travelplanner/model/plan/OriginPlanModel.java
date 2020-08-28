package com.laioffer.travelplanner.model.plan;

public class OriginPlanModel {
    private double lat;
    private double lon;

    public OriginPlanModel() {
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
