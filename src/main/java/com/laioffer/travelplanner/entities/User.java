package com.laioffer.travelplanner.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // specify the class is an entity and is mapped to a database table
@Table(name = "user") // specify the name of the database table to be used for mapping
public class User {
	@Id // represents the primary key
	private String userId;
	private String userName;
	private String password; // password type
	private String email;
	private String token;
	private Date createTime; // date/time type ==> do we need these fields in the class?
	private Date updateTime; // date/time type
	// private Date expireDate; //date/time type
	private List<String> planIds;

	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getPlanIds() {
		return planIds;
	}

	public void setPlanIds(List<String> planIds) {
		this.planIds = planIds;
	}

	@Override
	public String toString() {
		return "User{" + "email='" + email + '\'' + ", username='" + userName + '\'' + ", password='" + password + '\''
				+ ", timeCreate=" + createTime + ", timeUpdated=" + updateTime + ", token='" + token + '\'' + '}';
	}
}
