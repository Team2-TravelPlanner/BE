package com.laioffer.travelplanner.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "plans")

public class Plan {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer planId;
	
	private String planOwner;

	
	private Date createTime;
	private Date updateTime; //do we need these fields in the class?
	
	public Plan() {
		
	}
	public Plan(Integer planId, String planOwner) {
		this.planId = planId;
		this.planOwner = planOwner;
		
	}
	public String getPlanOwner() { //email
		return planOwner;
	}

	public void setPlanOwner(String planOwner) {
		this.planOwner = planOwner;
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
	@Override
	public String toString() {
		return "Plan [planId=" + planId + ", planOwner=" + planOwner + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}
	

	
	
	

}

