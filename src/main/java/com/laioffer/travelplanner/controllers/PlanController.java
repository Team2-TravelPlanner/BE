package com.laioffer.travelplanner.controllers;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.laioffer.travelplanner.antAlg.ACO;
import com.laioffer.travelplanner.entities.Place;
import io.swagger.models.auth.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @PostMapping("/customized")
    public ResponseEntity<?> generateCustomizedPlan(@RequestBody JSONObject jsonObject) {
        JSONArray places = jsonObject.getJSONArray("place");
        List<Place> placeList = JSONObject.parseArray(places.toJSONString(), Place.class);
        ACO aco = new ACO(placeList);
        aco.iterator();
        System.out.println(aco.getBestTour());

        Integer startDate = jsonObject.getJSONObject("settings").getInteger("startDate");
        Integer endDate = jsonObject.getJSONObject("settings").getInteger("endDate");

        Integer duration = (endDate - startDate) / (60 * 60 * 24);
        System.out.println(duration);





        return new ResponseEntity<>(aco.getBestTour(), HttpStatus.OK);
    }
}
