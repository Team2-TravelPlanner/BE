package com.laioffer.travelplanner.entities;

import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.Id;
import javax.persistence.Table;

import com.laioffer.travelplanner.enumerate.DayOfWeek;

@Entity
@Table(name = "businesshour")
public class BusinessHour {
	@Id
	private String businessHourId;

	private String placeId;

	@Enumerated(EnumType.STRING)
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
