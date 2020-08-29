package com.laioffer.travelplanner.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.DayOfWeek;
import java.util.Date;

@Document(indexName = "travel")
public class BusinessHour {
    @Id
    private String businessHourId;

    private String placeId;

    private DayOfWeek dayOfWeek;
    private Date startTime;
    private Date endTime;

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getBusinessHourId() {
        return businessHourId;
    }

    public void setBusinessHourId(String businessHourId) {
        this.businessHourId = businessHourId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

}
