package com.laioffer.travelplanner.repositories;

import com.laioffer.travelplanner.entities.Plan;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface PlanRepository extends ElasticsearchRepository<Plan, Integer> {
}
