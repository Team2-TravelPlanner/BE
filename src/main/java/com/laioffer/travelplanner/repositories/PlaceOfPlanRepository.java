package com.laioffer.travelplanner.repositories;

import com.laioffer.travelplanner.entities.PlaceOfPlan;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PlaceOfPlanRepository extends ElasticsearchRepository<PlaceOfPlan, Integer>{

}
