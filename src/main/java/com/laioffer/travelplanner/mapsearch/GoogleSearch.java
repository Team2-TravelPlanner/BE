package com.laioffer.travelplanner.mapsearch;

import com.google.maps.GeoApiContext;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlaceDetails;
import com.laioffer.travelplanner.entities.Category;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleSearch {

    @Bean
    public GooglePlaces googleClient() {
        return new GooglePlaces("AIzaSyBFNRmLV8bEPw15tE1fN79y-rlhTmw5Lso");
    }

    public List<Place> getInfo (String placeName) {
        return googleClient().getPlacesByQuery(placeName, GooglePlaces.MAXIMUM_RESULTS);
    }


    public static void main(String[] args) {
        GooglePlaces client = new GooglePlaces("AIzaSyBFNRmLV8bEPw15tE1fN79y-rlhTmw5Lso");
        List<Place> places = client.getPlacesByQuery("Brooklyn Bridge", GooglePlaces.MAXIMUM_RESULTS);

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyBFNRmLV8bEPw15tE1fN79y-rlhTmw5Lso")
                .build();

        PlaceDetailsRequest request = PlacesApi.placeDetails(context, places.get(0).getPlaceId());
        //request.fields(FieldMask.)
        try {
            PlaceDetails details = request.await();
            System.out.println(details.website);
            for (int i = 0; i < details.types.length; i++) {

                Category category = new Category();
                category.setCategoryName(details.types[i].name());
                if (category.getPlaceIds() != null) {
                    category.getPlaceIds().add(details.placeId);
                }
                else {

                }
                System.out.println(category.toString());
            }
            System.out.println(details.types);
//            System.out.println(details.rating);
//            System.out.println(details.name);
//            System.out.println(details.placeId);
//            System.out.println(details.formattedAddress);
//            System.out.println(details.adrAddress);
//            System.out.println(details.url);
//            System.out.println(details.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
