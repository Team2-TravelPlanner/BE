package com.laioffer.travelplanner.services;

import com.laioffer.travelplanner.entities.Place;

import java.util.List;

public interface PlaceSearchService {
    public Place searchPlaceIfNotExist(List<se.walkercrou.places.Place> places);
}
