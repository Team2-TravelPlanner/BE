package com.laioffer.travelplanner.controllers;

import com.laioffer.travelplanner.entities.Place;
import com.laioffer.travelplanner.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/places")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> searchPlaces(@RequestParam(name = "keyword", required = false) String keyword,
                                          @RequestParam(name = "category", required = false) String category,
                                          @RequestParam(name = "upper_left_lat") Double upperLeftLat,
                                          @RequestParam(name = "upper_left_lon") Double upperLeftLon,
                                          @RequestParam(name = "lower_right_lat") Double lowerRightLat,
                                          @RequestParam(name = "lower_right_lon") Double lowerRightLon,
                                          @RequestParam(name = "display_item_limit", required = false, defaultValue = "10") Integer displayItemLimit,
                                          @RequestParam(name = "current_page_number", required = false) Integer currentPageNumber) {

        Place place = placeService.searchPlacesByKeyword(keyword);

//        Place place = placeService.searchPlacesByGeolocation(upperLeftLat, upperLeftLon, lowerRightLat, lowerRightLon);

        return new ResponseEntity<>(place, HttpStatus.OK);
    }
}
