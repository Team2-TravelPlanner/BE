package com.laioffer.travelplanner.services.implementation;

import com.laioffer.travelplanner.entities.Place;
import com.laioffer.travelplanner.repositories.PlaceRepository;
import com.laioffer.travelplanner.services.PlaceService;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.index.query.GeoBoundingBoxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public Place searchPlacesByKeyword(String keyword) {
        return null;
    }

    @Override
    public Place searchPlacesByGeolocation(Double upperLeftLat, Double upperLeftLon, Double lowerRightLat, Double lowerRightLon) {
        GeoBoundingBoxQueryBuilder queryBuilder = QueryBuilders.geoBoundingBoxQuery("location")
                .setCorners(
                        GeoPoint.fromGeohash(String.valueOf(new GeoPoint(upperLeftLat, upperLeftLon))),
                        new GeoPoint(lowerRightLat, lowerRightLon)
                );

        return null;
    }
}
