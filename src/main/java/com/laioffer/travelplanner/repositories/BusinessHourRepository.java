package com.laioffer.travelplanner.repositories;

import com.laioffer.travelplanner.entities.BusinessHour;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BusinessHourRepository extends ElasticsearchRepository<BusinessHour, String>{
    public List<BusinessHour>  findBusinessHourByPlaceId(String placeId);
}
