package com.laioffer.travelplanner.repositories;

import com.laioffer.travelplanner.entities.BusinessHour;
import com.laioffer.travelplanner.entities.BusinessHourId;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BusinessHourRepository extends ElasticsearchRepository<BusinessHour, BusinessHourId>{

}
