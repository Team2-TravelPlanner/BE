package com.laioffer.travelplanner.services.implementation;
import com.laioffer.travelplanner.antAlg.ACO;
import com.laioffer.travelplanner.entities.Place;
import com.laioffer.travelplanner.mapsearch.GoogleSearch;
import com.laioffer.travelplanner.planModel.CustomizedPlan;
import com.laioffer.travelplanner.planModel.Origin;
import com.laioffer.travelplanner.repositories.PlaceRepository;
import com.laioffer.travelplanner.repositories.PlanRepository;
import com.laioffer.travelplanner.requestModel.RequestSettingsModel;
import com.laioffer.travelplanner.services.PlaceSearchService;
import com.laioffer.travelplanner.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class PlanServiceImp implements PlanService {
    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private GoogleSearch googleSearch;

    @Autowired
    private PlaceSearchService placeSearchService;



    @Override
    public CustomizedPlan generateCustomizedPlan(List<String> names, List<String> categories, RequestSettingsModel settings) {
        List<Place> placeList = new ArrayList<>();
        for (String name : names) {
//            Place place;
//            placeList.add(placeRepository.findByPlaceName(name).orElse
//                    (place = placeSearchService.searchPlaceIfNotExist(googleSearch.getInfo(name))));
//            placeRepository.save(place);
            Place place = placeRepository.findByPlaceName(name).orElse(null);
            if (place == null) {
                place = placeSearchService.searchPlaceIfNotExist(googleSearch.getInfo(name));
                placeRepository.save(place);
            }
            placeList.add(place);
        }
        ACO aco = new ACO(placeList);
        aco.iterator();

        CustomizedPlan customizedPlan = new CustomizedPlan();
        Origin origin = new Origin();
        origin.setLat(settings.getLat());
        origin.setLon(settings.getLon());
        customizedPlan.setStartDate(settings.getStartDate());
        customizedPlan.setEndDate(settings.getEndDate());
        customizedPlan.setPlaceDetails(aco.getPlaceDetails());
        customizedPlan.setOrigin(origin);
        return customizedPlan;
    }
}
