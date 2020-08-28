package com.laioffer.travelplanner.mapsearch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;

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

        List<Place> places = client.getPlacesByQuery("TimesSquare", GooglePlaces.MAXIMUM_RESULTS);
        System.out.println(places.get(0).getPlaceId());
        System.out.println(places.toString());
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
    }
}
