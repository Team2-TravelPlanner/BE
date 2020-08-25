package com.laioffer.travelplanner.controllers;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.laioffer.travelplanner.antAlg.ACO;
import com.laioffer.travelplanner.mapsearch.GoogleSearch;
import com.laioffer.travelplanner.planModel.CustomizedPlan;
import com.laioffer.travelplanner.planModel.Origin;
import com.laioffer.travelplanner.requestModel.RequestCustomizedPlan;
import com.laioffer.travelplanner.requestModel.RequestSettingsModel;
import com.laioffer.travelplanner.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.walkercrou.places.Place;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    @Autowired
    private GoogleSearch googleSearch;

    @PostMapping("/customized")
    public ResponseEntity<?> generateCustomizedPlan(@RequestBody RequestCustomizedPlan customizedPlan) {
//        JSONArray places = jsonObject.getJSONArray("place");
//        List<Place> placeList = JSONObject.parseArray(places.toJSONString(), Place.class);
//        ACO aco = new ACO(placeList);
//        aco.iterator();
//        System.out.println(aco.getOrder());
//
//        Integer startDate = jsonObject.getJSONObject("settings").getInteger("startDate");
//        Integer endDate = jsonObject.getJSONObject("settings").getInteger("endDate");
//
//        Integer duration = (endDate - startDate) / (60 * 60 * 24);
//        System.out.println(duration);
//
//        JSONArray res = JSONArray.parseArray(JSON.toJSONString(aco.getOrder(), SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.IgnoreErrorGetter));
//
//
//        CustomizedPlan customizedPlan = new CustomizedPlan();
//        Origin origin = new Origin();
//        customizedPlan.setStartDate(startDate);
//        customizedPlan.setEndDate(endDate);
//        customizedPlan.setPlaceDetails(aco.getPlaceDetails());
//        customizedPlan.setOrigin(origin);
//
//        String response = JSON.toJSONString(customizedPlan, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.IgnoreErrorGetter);
        RequestSettingsModel settings = customizedPlan.getSettings();

        CustomizedPlan res = planService.generateCustomizedPlan(customizedPlan.getPlaces(), customizedPlan.getCategories(), customizedPlan.getSettings());
        String response = JSON.toJSONString(res, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.IgnoreErrorGetter);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @GetMapping("/searchPlace")
    public ResponseEntity<?> getPlaceInfo(@RequestParam(name = "placeName") String placeName) {
        List<Place> places =  googleSearch.getInfo(placeName);

        com.laioffer.travelplanner.entities.Place place = new com.laioffer.travelplanner.entities.Place();
//        place.setPlaceName(places.get(0).getName());
//        place.setAddress(places.get(0).getAddress());
//        place.setLat(places.get(0).getLatitude());
//        place.setLon(places.get(0).getLongitude());
//        place.setPopularity((float) places.get(0).getRating());
//        place.setImageLink(places.get(0).getIconUrl());
//        place.setWebsite(places.get(0).getWebsite());




        return new ResponseEntity<>(place.toString(), HttpStatus.OK);
    }

}
