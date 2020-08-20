package com.laioffer.travelplanner.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.laioffer.travelplanner.enumerate.DayOfWeek;


@Entity
@Table (name = "businesshours")
public class BusinessHour {
	@Id
	private Integer businessHourId;
	
	private Integer placeId;
	private DayOfWeek dayOfWeek;
	private Date startTime;
	private Date endTime;
	
	public BusinessHour() {
		
	}
	public Integer getPlaceId() {
		return placeId;
	}
	public Integer getBusinessHourId() {
		return businessHourId;
	}
	public void setBusinessHourId(Integer businessHourId) {
		this.businessHourId = businessHourId;
	}
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
	
	

}
