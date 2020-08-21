package com.laioffer.travelplanner.repositories;

import com.laioffer.travelplanner.entities.BusinessHour;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BusinessHourRepository extends ElasticsearchRepository<BusinessHour, String>{

}
