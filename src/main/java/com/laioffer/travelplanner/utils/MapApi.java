package com.laioffer.travelplanner.utils;

import java.io.IOException;

import com.google.maps.GeoApiContext;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.PlaceDetailsRequest.FieldMask;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.OpeningHours;
import com.google.maps.model.Photo;
import com.google.maps.model.PlaceDetails;

public class MapApi {

	public static void main(String[] args) throws ApiException, InterruptedException, IOException {
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey("AIzaSyBFNRmLV8bEPw15tE1fN79y-rlhTmw5Lso")
			    .build();
		
		PlaceDetailsRequest request = PlacesApi.placeDetails(context, "ChIJaXQRs6lZwokRY6EFpJnhNNE");
		//request.fields(FieldMask.)
		PlaceDetails details = request.await();


		System.out.println(details.toString());


//		OpeningHours openingHours = details.openingHours;
//		OpeningHours.Period[] periods = openingHours.periods;
//		for (OpeningHours.Period period : periods) {
//			System.out.println(period.open.day.getName());
//
//			String open = period.open.time.toString();
//			String close = period.close.time.toString();
//			String openDay = period.open.day.getName();
//
//			System.out.println(open);
//			System.out.println(close);
//			System.out.println(openDay);
//		}


//		for(Photo ph : details.photos) {
//			System.out.println(ph.photoReference);
//		}
		//System.out.println(details.openingHours.toString());
		context.shutdown();
	}
}
