package com.laioffer.travelplanner.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity //specify the class is an entity and is mapped to a database table
@Table (name = "users") //specify the name of the database table to be used for mapping
public class User {
	private Integer userId;
	private String userName;
	private String password; //password type
	@Id //represents the primary key
	private String email;
	private String token;
	private Date createTime; //date/time type ==> do we need these fields in the class?
	private Date updateTime; //date/time type
	private Date expireDate; //date/time type
	private List<Plan> plans;
	
	public User() {
		
	}
	
	public User(Integer userId, String userName, String password, String email, String token, Date createTime,
			Date updateTime, Date expireDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.token = token;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.expireDate = expireDate;
	}

	public Integer getId() {
		return userId;
	}
	public void setId(Integer userId) {
		this.userId = userId;
	}
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
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", username='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", timeCreate=" + createTime +
                ", timeUpdated=" + updateTime +
                ", token='" + token + '\'' +
                '}';
    }
}
