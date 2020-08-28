package com.laioffer.travelplanner.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "travel")
public class User {
	@Id // represents the primary key
	private String email;
	
	private String userName;
	private String password; // password type
	
	private String token;
	private Date createTime; // date/time type ==> do we need these fields in the class?
	private Date updateTime; // date/time type
	// private Date expireDate; //date/time type
	private List<String> planIds;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public List<String> getPlanIds() {
		return planIds;
	}
	public void setPlanIds(List<String> planIds) {
		this.planIds = planIds;
	}

	
}
