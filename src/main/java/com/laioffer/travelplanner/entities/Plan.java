package com.laioffer.travelplanner.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "travel")
public class Plan {
    @Id
    private Integer id;

    private String planOwner;


    public Plan(Integer id, String planOwner) {
        this.id = id;
        this.planOwner = planOwner;
    }

    public Plan() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanOwner() {
        return planOwner;
    }

    public void setPlanOwner(String planOwner) {
        this.planOwner = planOwner;
    }

}
