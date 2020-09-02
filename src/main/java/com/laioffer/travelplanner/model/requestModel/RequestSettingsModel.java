package com.laioffer.travelplanner.model.requestModel;

import java.util.Date;

import io.swagger.models.auth.In;

public class RequestSettingsModel {
    private double lon;
    private double lat;
    private Date startDate;
    private Date endDate;
    private String travelStyle;

    public RequestSettingsModel() {
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }


    public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTravelStyle() {
        return travelStyle;
    }

    public void setTravelStyle(String travelStyle) {
        this.travelStyle = travelStyle;
    }
}
