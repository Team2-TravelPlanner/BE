package com.laioffer.travelplanner.repositories;

import com.laioffer.travelplanner.entities.PlaceOfPlan;
import com.laioffer.travelplanner.entities.PlaceOfPlanId;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PlaceOfPlanRepository extends ElasticsearchRepository<PlaceOfPlan, PlaceOfPlanId>{

}
