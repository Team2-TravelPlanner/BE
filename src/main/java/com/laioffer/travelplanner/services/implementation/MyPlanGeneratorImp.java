package com.laioffer.travelplanner.services.implementation;
import com.laioffer.travelplanner.entities.Place;
import com.laioffer.travelplanner.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class MyPlanGeneratorImp {
    @Autowired
    private PlaceRepository placeRepository;

    public List<Place> generate (List<String> placeNames) {
        List<Place> placeList = new ArrayList<>();
        for (String name : placeNames) {
            placeList.add(placeRepository.findByPlaceName(name).orElse(null));
        }
        return placeList;
    }
}
