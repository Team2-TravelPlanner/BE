package com.laioffer.travelplanner.services.implementation;




import com.google.maps.GeoApiContext;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.OpeningHours;
import com.google.maps.model.PlaceDetails;
import com.laioffer.travelplanner.entities.BusinessHour;
import com.laioffer.travelplanner.entities.Place;
import com.laioffer.travelplanner.repositories.BusinessHourRepository;
import com.laioffer.travelplanner.services.PlaceSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceSearchServiceImp implements PlaceSearchService {

    @Autowired
    private BusinessHourRepository businessHourRepository;

    @Override
    public Place searchPlaceIfNotExist(List<se.walkercrou.places.Place> places) throws InterruptedException, ApiException, IOException {
        Place place = new Place();
        place.setPlaceId(places.get(0).getPlaceId());
        place.setPlaceName(places.get(0).getName());
        place.setAddress(places.get(0).getAddress());
        place.setLat(places.get(0).getLatitude());
        place.setLon(places.get(0).getLongitude());
//        place.setImageLink(places.get(0).getIconUrl());
//        place.setWebsite(places.get(0).getWebsite());

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyBFNRmLV8bEPw15tE1fN79y-rlhTmw5Lso")
                .build();

        PlaceDetailsRequest request = PlacesApi.placeDetails(context, place.getPlaceId());
        //request.fields(FieldMask.)
        PlaceDetails details = request.await();

        place.setWebsite(details.website.toString());
        place.setPopularity(details.rating);
        place.setImageLink(details.icon.toString());


        OpeningHours openingHours = details.openingHours;
        if (openingHours != null) {
            OpeningHours.Period[] periods = openingHours.periods;
            for (OpeningHours.Period period : periods) {
                BusinessHour businessHour = new BusinessHour();
                businessHour.setPlaceId(place.getPlaceId());
                String open = period.open.time.toString();
                String close = period.close.time.toString();
                String openDay = period.open.day.getName();

                businessHour.setDayOfWeek(openDay);
                businessHour.setStartTime(open);
                businessHour.setStartTime(close);

                businessHourRepository.save(businessHour);
            }
        }

        List<String> businessHours = new ArrayList<>();
        List<BusinessHour> hours = businessHourRepository.findBusinessHourByPlaceId(place.getPlaceId());
        for (BusinessHour businessHour : hours) {
            businessHours.add(businessHour.getBusinessHourId());
        }
        place.setBusinesshourIds(businessHours);
        return place;
    }
}
