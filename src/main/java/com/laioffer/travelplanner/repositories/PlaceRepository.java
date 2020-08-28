package com.laioffer.travelplanner.repositories;

import com.laioffer.travelplanner.entities.Place;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Arrays;


public interface PlaceRepository extends ElasticsearchRepository<Place, String> {
    Iterable<Place> findAll();
}
