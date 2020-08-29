package com.laioffer.travelplanner.services.implementation;


import com.laioffer.travelplanner.entities.Place;
import com.laioffer.travelplanner.services.PlaceSearchService;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceSearchServiceImp implements PlaceSearchService {

    @Override
    public Place searchPlaceIfNotExist(List<se.walkercrou.places.Place> places) {
        Place place = new Place();
        place.setPlaceName(places.get(0).getName());
        place.setAddress(places.get(0).getAddress());
//        GeoPoint location = new GeoPoint(places.get(0).getLatitude(), places.get(0).getLongitude());
        Place.MyGeoPoint location = new Place.MyGeoPoint();
        location.setLat(places.get(0).getLatitude());
        location.setLat(places.get(0).getLongitude());
        place.setLocation(location);
        place.setPopularity((float) places.get(0).getRating());
        place.setImageLink(places.get(0).getIconUrl());
        place.setWebsite(places.get(0).getWebsite());
        return place;
    }
}
