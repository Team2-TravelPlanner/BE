package com.laioffer.travelplanner.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.laioffer.travelplanner.enumerate.DayOfWeek;


@Entity
@Table (name = "businesshours")
@IdClass(BusinessHourId.class)
public class BusinessHour {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer placeId;
	
	@Id
	private DayOfWeek dayOfWeek;
	private Date startTime;
	private Date endTime;
	
	public BusinessHour(Integer placeId, DayOfWeek dayOfWeek, Date startTime, Date endTime) {
		this.placeId = placeId;
		this.dayOfWeek = dayOfWeek;
		this.startTime = startTime;
		this.endTime = endTime;
		
		
	}
	public Integer getPlaceId() {
		return placeId;
	}
	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
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
