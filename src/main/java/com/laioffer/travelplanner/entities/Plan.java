package com.laioffer.travelplanner.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "plans")

public class Plan {
	@Id
	private Integer planId;
	
	private Integer userId;

	private List<DayOfPlan> days;
	
	private Date createTime;
	private Date updateTime; //do we need these fields in the class?
	
	public Plan() {
		
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
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
	public List<DayOfPlan> getDays(){
		return days;
	}
	public void setDays(List<DayOfPlan> days) {
		this.days = days;
	}
	
	@Override
	public String toString() {
		return "Plan [planId=" + planId + ", userId=" + userId + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}

	
	

	
	
	

}

