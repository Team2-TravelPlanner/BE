package com.laioffer.travelplanner.repositories;

import com.laioffer.travelplanner.entities.DayOfPlan;
import com.laioffer.travelplanner.entities.User;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DayOfPlanRepository extends ElasticsearchRepository<DayOfPlan, String>{

	 Optional<DayOfPlan> findById(String dayId);
}
