package com.laioffer.travelplanner.repositories;

import com.laioffer.travelplanner.entities.Place;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends ElasticsearchRepository<Place, String> {
	List<Place> findAllByPlaceNameStartsWith(String name);
	Optional<Place> findByPlaceName(String name);
	Optional<Place> findByPlaceId(String placeId);
    
//    Optional<Place> findByPlaceId(String placeId);
}
