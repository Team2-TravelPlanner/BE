package com.laioffer.travelplanner.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "travel")
public class DayOfPlan {
	@Id
	private String dayId;

	private List<String> placeOfPlanIds;

	private String planId;
	
	private Date createTime;
	private Date updateTime;

	// ...
	
	private Integer Index;

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

	public String getDayId() {
		return dayId;
	}

	public void setDayId(String dayId) {
		this.dayId = dayId;
	}

	public List<String> getPlaceOfPlanIds() {
		return placeOfPlanIds;
	}

	public void setPlaceOfPlanIds(List<String> placeOfPlanIds) {
		this.placeOfPlanIds = placeOfPlanIds;
	}

	public Integer getIndex() {
		return Index;
	}

	public void setIndex(Integer index) {
		Index = index;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

}
