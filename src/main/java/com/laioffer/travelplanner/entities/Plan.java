package com.laioffer.travelplanner.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "travel")
public class Plan {
	@Id
	private String planId;

	private String userId;

	private List<String> dayOfPlanIds;

	private Date createTime;
	private Date updateTime; // do we need these fields in the class?

	//...
	private Float startLatitude;
	private Float startLongitude;
	
	private Date startDate;
	private Date endDate;
	//....
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

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getDayOfPlanIds() {
		return dayOfPlanIds;
	}

	public void setDayOfPlanIds(List<String> dayOfPlanIds) {
		this.dayOfPlanIds = dayOfPlanIds;
	}

	public Float getStartLatitude() {
		return startLatitude;
	}

	public void setStartLatitude(Float startLatitude) {
		this.startLatitude = startLatitude;
	}

	public Float getStartLongitude() {
		return startLongitude;
	}

	public void setStartLongitude(Float startLongitude) {
		this.startLongitude = startLongitude;
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

}
