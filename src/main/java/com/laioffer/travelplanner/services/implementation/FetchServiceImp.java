package com.laioffer.travelplanner.services.implementation;


import com.google.maps.GeoApiContext;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.PlacesApi;
import com.google.maps.model.PlaceDetails;
import com.laioffer.travelplanner.entities.Category;
import com.laioffer.travelplanner.enumerate.CategoryEnum;
import com.laioffer.travelplanner.repositories.CategoryRepository;
import com.laioffer.travelplanner.repositories.PlaceRepository;
import com.laioffer.travelplanner.services.FetchService;
import com.laioffer.travelplanner.utils.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;

import java.util.*;

@Service
public class FetchServiceImp implements FetchService {

    @Bean
    public GooglePlaces gClient() {
        return new GooglePlaces("AIzaSyBFNRmLV8bEPw15tE1fN79y-rlhTmw5Lso");
    }

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PlaceRepository placeRepository;


    @Override
    public void FetchCategories(List<String> placeNames) {
        for (String name : placeNames) {
            List<Place> places = gClient().getPlacesByQuery(name, GooglePlaces.MAXIMUM_RESULTS);
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey("AIzaSyBFNRmLV8bEPw15tE1fN79y-rlhTmw5Lso")
                    .build();

            PlaceDetailsRequest request = PlacesApi.placeDetails(context, places.get(0).getPlaceId());
            //request.fields(FieldMask.)
            try {
                PlaceDetails details = request.await();
                com.laioffer.travelplanner.entities.Place place = placeRepository.findById(details.placeId).orElse(null);
                if (place ==null) {
                    place = new com.laioffer.travelplanner.entities.Place();
                    place.setPlaceId(details.placeId);
                    place.setPlaceName(details.name);
                    place.setAverageTime(7200000);
                    place.setPopularity(details.rating);
                    place.setAddress(details.adrAddress);
                    place.setLat(details.geometry.location.lat);
                    place.setLon(details.geometry.location.lng);
                    if (details.website != null) {
                        place.setWebsite(details.website.toString());
                    }
                    if (details.photos != null && details.photos.length > 0) {
                        String ref = details.photos[0].photoReference;
                        String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=1200&photoreference="
                                + ref
                                + "&key=";
                        place.setImageLink(url);
                    }
                    List<String> categoryIdList = new ArrayList<>();
                    place.setCategories(categoryIdList);
                    
                    for (int i = 0; i < details.types.length; i++) {
                        if (EnumUtils.ifInclude(details.types[i].name())) {
                            place.getCategories().add(details.types[i].name());
                            Category category = categoryRepository.findByCategoryName(details.types[i].name()).orElse(null);
                            if (category == null) {
                                category = new Category();
                                category.setCategoryName(details.types[i].name());
                            }


                            if (category.getPlaceIds() != null) {
                                category.getPlaceIds().add(details.placeId);
                            }
                            else {
                                List<String> idList = new ArrayList<>();
                                idList.add(details.placeId);
                                category.setPlaceIds(idList);
                            }

                            categoryRepository.save(category);
                        }
                    }
                    placeRepository.save(place);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
