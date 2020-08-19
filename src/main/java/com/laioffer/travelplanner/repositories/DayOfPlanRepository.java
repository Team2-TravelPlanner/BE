package com.laioffer.travelplanner.repositories;

import com.laioffer.travelplanner.entities.DayOfPlan;
import com.laioffer.travelplanner.entities.DayOfPlanId;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DayOfPlanRepository extends ElasticsearchRepository<DayOfPlan, DayOfPlanId>{

}
