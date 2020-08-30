package com.laioffer.travelplanner.repositories;

import com.laioffer.travelplanner.entities.Place;
import com.laioffer.travelplanner.entities.Plan;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Date;
import java.util.Optional;


public interface PlanRepository extends ElasticsearchRepository<Plan, String> {
    Optional<Float> findByStartLatitude(Float startLatitude);
    Optional<Float> findByStartLongitude(Float startLongitude);
//    Optional<Float> findByStartDate(Date startDate);
//    Optional<Float> findByEndDate(Date endDate);
}
