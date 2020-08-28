package com.laioffer.travelplanner.utils;

import java.io.IOException;

import com.google.maps.GeoApiContext;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.PlaceDetailsRequest.FieldMask;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.Photo;
import com.google.maps.model.PlaceDetails;

public class MapApi {

	public static void main(String[] args) throws ApiException, InterruptedException, IOException {
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey("your-key")
			    .build();
		
		PlaceDetailsRequest request = PlacesApi.placeDetails(context, "ChIJi6C1MxquEmsR9-c-3O48ykI");
		//request.fields(FieldMask.)
		PlaceDetails details = request.await();

		
//		for(Photo ph : details.photos) {
//			System.out.println(ph.photoReference);
//		}
		//System.out.println(details.openingHours.toString());
		context.shutdown();
	}
}
