package com.laioffer.travelplanner.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table (name = "dayofplans")
@IdClass(DayOfPlanId.class)

public class DayOfPlan {
	@Id
	private Integer planId;
	@Id
	private Integer dayId;
	
	private Date createTime;
	private Date updateTime;

	public DayOfPlan(Integer planId, Integer dayId) {
		this.planId = planId;
		this.dayId = dayId;
	}

	public Integer getPlanId() {
		return planId;
	}

	public Integer getDayId() {
		return dayId;
	}

	public void setDayId(Integer dayId) {
		this.dayId = dayId;
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

