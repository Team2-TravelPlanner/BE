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
            for (int i = 0; i < details.photos.length; i++) {
                System.out.println(details.photos[i].htmlAttributions[0]);
                System.out.println(details.photos[i].photoReference);

            }
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



//
//        List<Place> places = client.getPlacesByQuery("Empire State Building", GooglePlaces.MAXIMUM_RESULTS);
//
//        Place empireStateBuilding = null;
//        for (Place place : places) {
//            if (place.getName().equals("Times Square")) {
//                empireStateBuilding = place;
//                break;
//            }
//        }
//
//        if (empireStateBuilding != null) { ; // sends a GET request for more details
//            // Just an example of the amount of information at your disposal:
//            System.out.println("Name: " + empireStateBuilding.getName());
//            System.out.println("Phone: " + empireStateBuilding.getPhoneNumber());
//            System.out.println("International Phone: " + empireStateBuilding.getInternationalPhoneNumber());
//            System.out.println("Website: " + empireStateBuilding.getWebsite());
//            System.out.println("Status: " + empireStateBuilding.getStatus());
//            System.out.println("Google Place URL: " + empireStateBuilding.getGoogleUrl());
//            System.out.println("Price: " + empireStateBuilding.getPrice());
//            System.out.println("Address: " + empireStateBuilding.getAddress());
//            System.out.println("Vicinity: " + empireStateBuilding.getVicinity());
//            System.out.println("Reviews: " + empireStateBuilding.getReviews().size());
//            System.out.println("Hours:\n " + empireStateBuilding.getHours());
//        }
//
//        System.out.println(places.get(0).getPlaceId());
//        System.out.println(places.toString());
//
////        Place place = client.getPlaceById(places.get(0).getPlaceId());
////        System.out.println(place.toString());
////        Place empireStateBuilding = null;
////        for (Place place : places) {
////            if (place.getName().equals("Empire State Building")) {
////                empireStateBuilding = place;
////                break;
////            }
////        }
////
////        if (empireStateBuilding != null) { ; // sends a GET request for more details
////            // Just an example of the amount of information at your disposal:
////            Place detailedEmpireStateBuilding = empireStateBuilding.getDetails();
////            System.out.println("Name: " + empireStateBuilding.getName());
////            System.out.println("Phone: " + empireStateBuilding.getPhoneNumber());
////            System.out.println("International Phone: " + empireStateBuilding.getInternationalPhoneNumber());
////            System.out.println("Website: " + empireStateBuilding.getWebsite());
////            System.out.println("Status: " + empireStateBuilding.getStatus());
////            System.out.println("Google Place URL: " + empireStateBuilding.getGoogleUrl());
////            System.out.println("Price: " + empireStateBuilding.getPrice());
////            System.out.println("Address: " + empireStateBuilding.getAddress());
////            System.out.println("Vicinity: " + empireStateBuilding.getVicinity());
////            System.out.println("Reviews: " + empireStateBuilding.getReviews().size());
////            System.out.println("Hours:\n " + empireStateBuilding.getHours());
////        }
    }
}
