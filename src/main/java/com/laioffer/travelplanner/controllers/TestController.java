package com.laioffer.travelplanner.controllers;

import com.laioffer.travelplanner.model.common.CustomizedPlanRequestModel;
import com.laioffer.travelplanner.services.FetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private FetchService fetchService;

    @GetMapping("/testAuth")
    public String testAuth() {
        return "Passed Authorization";
    }


    @PostMapping("/insertData")
    public ResponseEntity<?> insertCategories(@RequestBody CustomizedPlanRequestModel customizedPlan) {
        List<String> places = customizedPlan.getPlaces();
        fetchService.FetchCategories(places);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
