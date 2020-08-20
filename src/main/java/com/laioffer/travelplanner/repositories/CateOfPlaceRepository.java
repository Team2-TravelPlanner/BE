package com.laioffer.travelplanner.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.laioffer.travelplanner.entities.CateOfPlace;

public interface CateOfPlaceRepository extends ElasticsearchRepository<CateOfPlace, Integer>{

}
