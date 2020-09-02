package com.laioffer.travelplanner.repositories;

import com.laioffer.travelplanner.entities.Place;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface PlaceRepository extends ElasticsearchRepository<Place, String> {
	Iterable<Place> findAll();


	Optional<Place> findByPlaceName(String name);
	Optional<Place> findByPlaceId(String placeId);
    
//    Optional<Place> findByPlaceId(String placeId);
}
