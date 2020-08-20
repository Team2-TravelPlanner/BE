package com.laioffer.travelplanner.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "dayofplans")

public class DayOfPlan {
	@Id
	private Integer dayId;
	
	private List<PlaceOfPlan> placeOfPlan;
	

	
	private Date createTime;
	private Date updateTime;

	public DayOfPlan() {
		
	}
	
	public Integer getDayId() {
		return dayId;
	}


	public void setDayId(Integer dayId) {
		this.dayId = dayId;
	}
	
	public void setPlaceOfPlan(List<PlaceOfPlan> placeOfPlan) {
		this.placeOfPlan = placeOfPlan;
	}

	
	public List<PlaceOfPlan> getPlaceOfPlan() {
		return placeOfPlan;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	

}

