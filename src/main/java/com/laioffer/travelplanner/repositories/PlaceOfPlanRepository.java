package com.laioffer.travelplanner.repositories;

import com.laioffer.travelplanner.entities.PlaceOfPlan;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PlaceOfPlanRepository extends ElasticsearchRepository<PlaceOfPlan, String>{
	Optional<PlaceOfPlan> findByPlaceOfPlanId(String placeOfPlanId);
}
