package com.laioffer.travelplanner.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;
import java.util.List;

@Document(indexName = "travel")
public class User {

    @Id
    private String email;

    private String username;

    private String password;

    private Date timeCreate;

    private Date timeUpdated;

    private List<Plan> plans;

    @Transient
    private String token;

    public User() {
    }


    public User(String email, String username, String password, Date timeCreate, Date timeUpdated, String token) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.timeCreate = timeCreate;
        this.timeUpdated = timeUpdated;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Date getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(Date timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", timeCreate=" + timeCreate +
                ", timeUpdated=" + timeUpdated +
                ", token='" + token + '\'' +
                '}';
    }
}
