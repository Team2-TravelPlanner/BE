package com.laioffer.travelplanner.services;

import com.google.maps.errors.ApiException;
import com.laioffer.travelplanner.entities.Place;

import java.io.IOException;
import java.util.List;

public interface PlaceSearchService {
    public Place searchPlaceIfNotExist(List<se.walkercrou.places.Place> places) throws InterruptedException, ApiException, IOException;
}
