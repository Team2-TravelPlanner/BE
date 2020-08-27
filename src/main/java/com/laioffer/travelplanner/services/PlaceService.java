package com.laioffer.travelplanner.services;

import com.laioffer.travelplanner.entities.Place;

public interface PlaceService {
    public Place searchPlacesByKeyword(String keyword);
    public Place searchPlacesByGeolocation(Double upperLeftLat, Double upperLeftLon, Double lowerRightLat, Double lowerRightLon);
}