package com.laioffer.travelplanner.services.implementation;


import com.laioffer.travelplanner.entities.Place;
import com.laioffer.travelplanner.services.PlaceSearchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceSearchServiceImp implements PlaceSearchService {

    @Override
    public Place searchPlaceIfNotExist(List<se.walkercrou.places.Place> places) {
        Place place = new Place();
        place.setPlaceName(places.get(0).getName());
        place.setAddress(places.get(0).getAddress());
        place.setLat(places.get(0).getLatitude());
        place.setLon(places.get(0).getLongitude());
        place.setPopularity((float) places.get(0).getRating());
        place.setImageLink(places.get(0).getIconUrl());
        place.setWebsite(places.get(0).getWebsite());
        return place;
    }
}
