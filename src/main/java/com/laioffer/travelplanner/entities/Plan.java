package com.laioffer.travelplanner.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;
import java.util.List;

@Document(indexName = "travel")
public class Plan {
    @Id
    private String planId;

    private String userId;

    private List<String> dayOfPlanIds;

    private Date createTime;
    private Date updateTime; // do we need these fields in the class?

    //...

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
}
