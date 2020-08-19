package com.laioffer.travelplanner.repositories;

import com.laioffer.travelplanner.entities.Place;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PlaceRepository extends ElasticsearchRepository<Place, Integer> {

}
